package com.onlinelearning.service.impl;

import com.onlinelearning.mapper.QuestionMapper;
import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.QuestionCreateRequestDto;
import com.onlinelearning.model.dto.request.QuestionUpdateRequestDto;
import com.onlinelearning.model.dto.response.QuestionDetailResponseDto;
import com.onlinelearning.model.dto.response.QuestionForDeleteQuestionResponseDto;
import com.onlinelearning.model.dto.response.QuestionListForManagerQuestionResponseDto;
import com.onlinelearning.model.entity.Question;
import com.onlinelearning.model.entity.Quiz;
import com.onlinelearning.repository.CourseRepository;
import com.onlinelearning.repository.QuestionRepository;
import com.onlinelearning.repository.QuizRepository;
import com.onlinelearning.service.ManagerQuestionService;
import com.onlinelearning.service.common.BaseService;
import jakarta.persistence.Tuple;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerQuestionServiceImpl extends BaseService implements ManagerQuestionService {

    final QuizRepository quizRepository;
    final QuestionRepository questionRepository;
    final CourseRepository courseRepository;
    final QuestionMapper questionMapper;

    @Override
    public ResponseBase list(int quizId, long userId) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        Tuple tuple = quizRepository.getLessonIdAndCourseId(quizId);
        if (tuple == null) {
            data.put("error", "Quiz not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        int lessonId = tuple.get("lessonId", Integer.class);
        int courseId = tuple.get("courseId", Integer.class);

        if (!courseRepository.checkCourseTeacherExist(courseId, userId)) {
            return new ResponseBase("redirect:/ManagerCourse", data);
        }

        List<QuestionListForManagerQuestionResponseDto> questions = questionRepository.getQuestionsForManagerQuestion(quizId);
        data.put("questions", questions);
        data.put("quizId", quizId);
        data.put("lessonId", lessonId);
        return new ResponseBase("manager_question/list", data);
    }

    @Override
    public ResponseBase create(int quizId, long userId) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        Long creatorId = quizRepository.getCreatorId(quizId);
        if (creatorId == null) {
            data.put("error", "Quiz not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        if (creatorId != userId) {
            return new ResponseBase("redirect:/ManagerCourse", data);
        }

        data.put("quizId", quizId);
        return new ResponseBase("manager_question/create", data);
    }

    @Override
    public ResponseBase create(QuestionCreateRequestDto DTO) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        Quiz quiz = quizRepository.getQuizForManagerQuestion(DTO.getQuizId());
        if (quiz == null) {
            data.put("error", "Quiz not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        Question question = questionMapper.toQuestion(DTO);
        question.setQuiz(quiz);
        question.setAnswerCorrect(DTO.getAnswerCorrect());
        questionRepository.save(question);

        data.put("quizId", DTO.getQuizId());
        data.put("success", "Create successful");
        return new ResponseBase("manager_question/create", data);
    }

    private ResponseBase getResponseForDetailUpdate(int questionId, long userId, String viewName) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        QuestionDetailResponseDto detail = questionRepository.getQuestionDetail(questionId);
        if (detail == null) {
            data.put("error", "Lesson not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        if (detail.getCreatorId() != userId) {
            return new ResponseBase("redirect:/ManagerCourse", data);
        }

        detail.setAnswerCorrect(detail.getQuestion().getAnswerCorrect());
        data.put("detail", detail);
        return new ResponseBase(viewName, data);
    }

    @Override
    public ResponseBase detail(int questionId, long userId) {
        return getResponseForDetailUpdate(questionId, userId, "manager_question/detail");
    }

    @Override
    public ResponseBase update(int questionId, long userId) {
        return getResponseForDetailUpdate(questionId, userId, "manager_question/update");
    }

    @Override
    public ResponseBase update(int questionId, QuestionUpdateRequestDto DTO) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        QuestionDetailResponseDto detail = questionRepository.getQuestionDetail(questionId);
        if (detail == null) {
            data.put("error", "Lesson not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        detail.getQuestion().setQuestionName(DTO.getQuestionName().trim());
        detail.getQuestion().setAnswer1(DTO.getAnswer1().trim());
        detail.getQuestion().setAnswer2(DTO.getAnswer2().trim());
        detail.getQuestion().setAnswer3(DTO.getAnswer3().trim().isEmpty() ? null : DTO.getAnswer3().trim());
        detail.getQuestion().setAnswer4(DTO.getAnswer4().trim().isEmpty() ? null : DTO.getAnswer4().trim());
        detail.getQuestion().setAnswerCorrect(DTO.getAnswerCorrect());
        questionRepository.save(detail.getQuestion());

        detail.setAnswerCorrect(detail.getQuestion().getAnswerCorrect());
        data.put("detail", detail);
        data.put("success", "Update successful");
        return new ResponseBase("manager_question/update", data);
    }

    @Override
    public ResponseBase delete(int questionId, long userId) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        QuestionForDeleteQuestionResponseDto question = questionRepository.getQuestionInfoForDeleteQuestion(questionId);
        if (question == null) {
            data.put("error", "Lesson not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        if (!courseRepository.checkCourseTeacherExist(question.getCourseId(), userId)) {
            return new ResponseBase("redirect:/ManagerCourse", data);
        }

        questionRepository.deleteById(questionId);
        return new ResponseBase("redirect:/ManagerQuestion/" + question.getQuizId(), data);
    }
}
