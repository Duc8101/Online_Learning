package com.onlinelearning.service.impl;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.QuizCreateRequestDto;
import com.onlinelearning.model.dto.request.QuizUpdateRequestDto;
import com.onlinelearning.model.dto.response.QuizListResponseDto;
import com.onlinelearning.model.dto.response.QuizUpdateResponseDto;
import com.onlinelearning.model.entity.Lesson;
import com.onlinelearning.model.entity.Quiz;
import com.onlinelearning.repository.CourseRepository;
import com.onlinelearning.repository.LessonRepository;
import com.onlinelearning.repository.QuizRepository;
import com.onlinelearning.service.ManagerQuizService;
import com.onlinelearning.service.common.BaseService;
import jakarta.persistence.Tuple;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerQuizServiceImpl extends BaseService implements ManagerQuizService {

    final LessonRepository lessonRepository;
    final QuizRepository quizRepository;
    final CourseRepository courseRepository;

    @Override
    public ResponseBase list(int lessonId, long userId) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        Integer courseId = lessonRepository.getCourseId(lessonId);
        if (courseId == null) {
            data.put("error", "Lesson not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        if (!courseRepository.checkCourseTeacherExist(courseId, userId)) {
            return new ResponseBase("redirect:/ManagerCourse", data);
        }

        List<QuizListResponseDto> quizzes = quizRepository.getQuizzesForManagerQuiz(lessonId);

        data.put("quizzes", quizzes);
        data.put("lessonId", lessonId);
        data.put("courseId", courseId);
        return new ResponseBase("manager_quiz/list", data);
    }

    @Override
    public ResponseBase create(int lessonId, long userId) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        Integer courseId = lessonRepository.getCourseId(lessonId);
        if (courseId == null) {
            data.put("error", "Lesson not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        if (!courseRepository.checkCourseTeacherExist(courseId, userId)) {
            return new ResponseBase("redirect:/ManagerCourse", data);
        }

        data.put("lessonId", lessonId);
        return new ResponseBase("manager_quiz/create", data);
    }

    @Override
    public ResponseBase create(QuizCreateRequestDto DTO) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        Lesson lesson = lessonRepository.getByLessonId(DTO.getLessonId());
        if (lesson == null) {
            data.put("error", "Lesson not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        if (quizRepository.isQuizExist(DTO.getQuizName().trim(), DTO.getLessonId())) {
            data.put("error", "Quiz already exists");
            return new ResponseBase("manager_quiz/create", data);
        }

        Quiz quiz = Quiz.builder()
                .lesson(lesson)
                .quizName(DTO.getQuizName().trim()).build();
        quizRepository.save(quiz);

        data.put("success", "Create successful");
        data.put("lessonId", lesson.getLessonId());
        return new ResponseBase("manager_quiz/create", data);
    }

    @Override
    public ResponseBase update(int quizId, long userId) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        QuizUpdateResponseDto quiz = quizRepository.getQuizForManagerQuiz(quizId);
        if (quiz == null) {
            data.put("error", "Quiz not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        if (quiz.getCreatorId() != userId) {
            return new ResponseBase("redirect:/ManagerCourse", data);
        }

        data.put("quiz", new QuizListResponseDto(quiz.getQuizId(), quiz.getQuizName(), quiz.getLessonId()));
        return new ResponseBase("manager_quiz/update", data);
    }

    @Override
    public ResponseBase update(int quizId, QuizUpdateRequestDto DTO) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        QuizUpdateResponseDto quiz = quizRepository.getQuizForManagerQuiz(quizId);
        if (quiz == null) {
            data.put("error", "Quiz not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        data.put("quiz", new QuizListResponseDto(quiz.getQuizId(), quiz.getQuizName(), quiz.getLessonId()));

        if (quizRepository.isQuizExist(DTO.getQuizName().trim(), quiz.getLessonId(), quizId)) {
            data.put("error", "Quiz already exists");
            return new ResponseBase("manager_quiz/update", data);
        }

        quiz.setQuizName(DTO.getQuizName().trim());
        quizRepository.updateQuiz(quiz.getQuizName(), Instant.now(), quizId);

        data.put("quiz", new QuizListResponseDto(quiz.getQuizId(), quiz.getQuizName(), quiz.getLessonId()));
        data.put("success", "Update successful");
        return new ResponseBase("manager_quiz/update", data);
    }

    @Override
    public ResponseBase delete(int quizId, long userId) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        Tuple tuple = quizRepository.getLessonIdAndCourseId(quizId);
        if (tuple == null) {
            data.put("error", "Quiz not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        int courseId = tuple.get("courseId", Integer.class);
        int lessonId = tuple.get("lessonId", Integer.class);

        if (!courseRepository.checkCourseTeacherExist(courseId, userId)) {
            return new ResponseBase("redirect:/ManagerCourse", data);
        }

        quizRepository.deleteById(quizId);
        return new ResponseBase("redirect:/ManagerQuiz/" + lessonId, data);
    }
}
