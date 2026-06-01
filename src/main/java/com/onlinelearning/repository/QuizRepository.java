package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.QuizListResponseDto;
import com.onlinelearning.model.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    @Query("select new com.onlinelearning.model.dto.response.QuizListResponseDto(q.quizId, q.quizName, q.lesson.lessonId) FROM Quiz q\n"
            + "where q.lesson.lessonId = :lessonId and SIZE(q.questions) > 0")
    List<QuizListResponseDto> getQuizzesForTakeQuiz(int lessonId);
}
