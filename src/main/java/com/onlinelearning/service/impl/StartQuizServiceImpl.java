package com.onlinelearning.service.impl;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.StartQuizRequestDto;
import com.onlinelearning.model.dto.response.QuestionListForStartQuizResponseDto;
import com.onlinelearning.model.dto.response.ResultDetailResponseDto;
import com.onlinelearning.model.entity.*;
import com.onlinelearning.model.enumeration.Answer;
import com.onlinelearning.repository.*;
import com.onlinelearning.service.StartQuizService;
import com.onlinelearning.service.common.BaseService;
import jakarta.persistence.Tuple;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StartQuizServiceImpl extends BaseService implements StartQuizService {

    final QuizRepository quizRepository;
    final EnrollCourseRepository enrollCourseRepository;
    final ResultRepository resultRepository;
    final StartQuizRepository startQuizRepository;
    final QuestionRepository questionRepository;
    final UserRepository userRepository;

    private void setAnswers(Map<String, Object> data) {
        data.put("answer1", Answer.ANSWER1.getValue());
        data.put("answer2", Answer.ANSWER2.getValue());
        data.put("answer3", Answer.ANSWER3.getValue());
        data.put("answer4", Answer.ANSWER4.getValue());
    }

    @Override
    public ResponseBase startQuiz(int quizId, long userId) {
        Map<String, Object> data = new HashMap<>();
        Tuple tuple = quizRepository.getLessonIdAndCourseId(quizId);
        if (tuple == null) {
            setValueForHeaderFooter(data, true, true, true, true);
            data.put("error", "Quiz not found");
            return new ResponseBase("shared/error", data);
        }

        int lessonId = tuple.get("lessonId", Integer.class);
        int courseId = tuple.get("courseId", Integer.class);

        // if not enroll course
        if (!enrollCourseRepository.checkStudentEnrollCourse(courseId, userId)) {
            return new ResponseBase("redirect:/MyCourse", data);
        }

        int numberQuestion = questionRepository.getNumberQuestion(quizId);
        // if not exist question
        if (numberQuestion == 0) {
            data.put("lessonId", lessonId);
            return new ResponseBase(String.format("redirect:/TakeQuiz?lessonId=%d", lessonId), data);
        }

        setValueForHeaderFooter(data, false, true, false, false);

        List<QuestionListForStartQuizResponseDto> questions = questionRepository.getQuestionsForStartQuiz(quizId,
                PageRequest.of(0, 1));

        // delete start quiz of student
        startQuizRepository.deleteAllByStudentId(userId);

        List<ResultDetailResponseDto> results = resultRepository.getLatestResult(quizId, userId, PageRequest.of(0, 1));
        data.put("result", results.isEmpty() ? null : results.getFirst());
        data.put("question", questions.getFirst());
        data.put("quizId", quizId);
        data.put("minutes", 4);
        data.put("seconds", 59);
        data.put("question_no", 1);
        data.put("button", numberQuestion == 1 ? "Finish" : "Next");
        setAnswers(data);
        return new ResponseBase("start_quiz", data);
    }

    @Override
    @Transactional
    public ResponseBase startQuiz(StartQuizRequestDto DTO, int minutes, int questionNo, int seconds, long userId, String button) {
        if ("Next".equals(button)) {
            return next(DTO, minutes, questionNo, seconds, userId);
        }

        if ("Back".equals(button)) {
            return previous(DTO, minutes, questionNo, seconds, userId);
        }
        return finish(DTO, userId);
    }

    private ResponseBase next(StartQuizRequestDto DTO, int minutes, int questionNo, int seconds, long userId) {
        Map<String, Object> data = new HashMap<>();
        List<QuestionListForStartQuizResponseDto> questions = questionRepository.getQuestionsForStartQuiz(DTO.getQuizId()
                , PageRequest.of(questionNo, 1));
        setDataForPreviousNext(data, DTO, questions, minutes, seconds, userId);
        data.put("question_no", questionNo + 1);

        int numberQuestion = questionRepository.getNumberQuestion(DTO.getQuizId());
        data.put("button", questionNo + 1 == numberQuestion ? "Finish" : "Next");
        setAnswers(data);
        return new ResponseBase("start_quiz", data);
    }

    private void setDataForPreviousNext(Map<String, Object> data, StartQuizRequestDto DTO, List<QuestionListForStartQuizResponseDto> questions, int minutes, int seconds, long userId) {
        String startQuizId = startQuizRepository.getStartQuizId(DTO.getQuestionId(), userId);

        // if not answer current question yet
        if (startQuizId == null) {
            User student = userRepository.findById(userId).orElse(null);
            Question question = questionRepository.findById(DTO.getQuestionId()).orElse(null);

            StartQuiz startQuiz = new StartQuiz();
            startQuiz.setQuestion(question);
            startQuiz.setStudent(student);
            startQuiz.setAnswer(DTO.getAnswer());
            startQuizRepository.save(startQuiz);
        } else {
            startQuizRepository.updateAnswer(DTO.getAnswer(), startQuizId);
        }

        setValueForHeaderFooter(data, false, true, false, false);

        if (!questions.isEmpty()) {
            List<Integer> answers = startQuizRepository.getAnswers(questions.getFirst().getQuestionId(), userId, PageRequest.of(0, 1));
            questions.getFirst().setChosenAnswer(answers.isEmpty() ? null : answers.getFirst());
        }

        List<ResultDetailResponseDto> results = resultRepository.getLatestResult(DTO.getQuizId(), userId, PageRequest.of(0, 1));
        data.put("result", results.isEmpty() ? null : results.getFirst());
        data.put("question", questions.getFirst());
        data.put("quizId", DTO.getQuizId());
        data.put("minutes", minutes);
        data.put("seconds", seconds);
        setAnswers(data);
    }

    private ResponseBase previous(StartQuizRequestDto DTO, int minutes, int questionNo, int seconds, long userId) {
        Map<String, Object> data = new HashMap<>();
        List<QuestionListForStartQuizResponseDto> questions = questionRepository.getQuestionsForStartQuiz(DTO.getQuizId()
                , PageRequest.of(questionNo - 2, 1));
        setDataForPreviousNext(data, DTO, questions, minutes, seconds, userId);
        data.put("question_no", questionNo - 1);
        data.put("button", "Next");
        return new ResponseBase("start_quiz", data);
    }

    private ResponseBase finish(StartQuizRequestDto DTO, long userId) {
        Map<String, Object> data = new HashMap<>();
        String startQuizId = startQuizRepository.getStartQuizId(DTO.getQuestionId(), userId);

        // if not answer current question yet
        if (startQuizId == null)  {
            User student = userRepository.findById(userId).orElse(null);
            Question question = questionRepository.findById(DTO.getQuestionId()).orElse(null);
            StartQuiz startQuiz = new StartQuiz();
            startQuiz.setQuestion(question);
            startQuiz.setStudent(student);
            startQuiz.setAnswer(DTO.getAnswer());
            startQuizRepository.save(startQuiz);
        } else {
            startQuizRepository.updateAnswer(DTO.getAnswer(), startQuizId);
        }

        setValueForHeaderFooter(data, false, true, false, false);

        List<Integer> chosenAnswers = startQuizRepository.getChosenAnswers(DTO.getQuizId(), userId);
        List<Integer> answerCorrect = questionRepository.getAnswersCorrect(DTO.getQuizId(), userId);
        int numberQuestion = questionRepository.getNumberQuestion(DTO.getQuizId());
        int score = 0;

        for (int i = 0; i < chosenAnswers.size(); i++) {
            if (Objects.equals(chosenAnswers.get(i), answerCorrect.get(i))) {
                score++;
            }
        }

        double finalScore = (double) score / numberQuestion * 10;

        startQuizRepository.deleteAllByStudentIdAndQuizId(userId, DTO.getQuizId());

        Quiz quiz = quizRepository.findById(DTO.getQuizId()).orElse(null);
        User student = userRepository.findById(userId).orElse(null);

        Result result = new Result();
        result.setQuiz(quiz);
        result.setScore(finalScore);
        result.setStudent(student);
        resultRepository.save(result);
        return new ResponseBase(String.format("redirect:/Result?quizId=%d", DTO.getQuizId()), data);
    }
}
