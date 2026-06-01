package com.onlinelearning.service.impl;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.response.QuizListResponseDto;
import com.onlinelearning.repository.EnrollCourseRepository;
import com.onlinelearning.repository.LessonRepository;
import com.onlinelearning.repository.QuizRepository;
import com.onlinelearning.service.TakeQuizService;
import com.onlinelearning.service.common.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TakeQuizServiceImpl extends BaseService implements TakeQuizService {

    private final LessonRepository lessonRepository;
    private final QuizRepository quizRepository;
    private final EnrollCourseRepository enrollCourseRepository;

    @Override
    public ResponseBase takeQuiz(int lessonId, long userId) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        Integer courseId = lessonRepository.getCourseId(lessonId);
        if (courseId == null) {
            data.put("error", "Lesson not found");
            return new ResponseBase("shared/error", data);
        }

        // if student does not enroll course
        if (!enrollCourseRepository.checkStudentEnrollCourse(courseId, userId)) {
            return new ResponseBase("redirect:/MyCourse", data);
        }

        List<QuizListResponseDto> quizzes = quizRepository.getQuizzesForTakeQuiz(lessonId);
        if (quizzes.isEmpty()) {
            return new ResponseBase("redirect:/MyCourse", data);
        }

        data.put("quizzes", quizzes);
        return new ResponseBase("take_quiz", data);
    }
}
