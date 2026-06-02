package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.QuizListResponseDto;
import com.onlinelearning.model.dto.response.QuizUpdateResponseDto;
import com.onlinelearning.model.entity.Quiz;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    @Query("select new com.onlinelearning.model.dto.response.QuizListResponseDto(q.quizId, q.quizName, q.lesson.lessonId) FROM Quiz q\n"
            + "where q.lesson.lessonId = :lessonId and SIZE(q.questions) > 0")
    List<QuizListResponseDto> getQuizzesForTakeQuiz(int lessonId);

    @Query("select q.lesson.lessonId as lessonId, q.lesson.course.courseId as courseId from Quiz q where q.quizId = :quizId and q.lesson.course.deleted = false")
    Tuple getLessonIdAndCourseId(int quizId);

    @Query("""
            select new com.onlinelearning.model.dto.response.QuizListResponseDto(q.quizId, q.quizName, q.lesson.lessonId) FROM Quiz q
            where q.lesson.lessonId = :lessonId
            order by CASE WHEN q.updatedAt IS NULL THEN q.createdAt ELSE q.updatedAt END desc""")
    List<QuizListResponseDto>  getQuizzesForManagerQuiz(int lessonId);

    @Query("select exists (select 1 from Quiz q where q.quizName = :quizName and q.lesson.lessonId = :lessonId)")
    boolean isQuizExist(String quizName, int lessonId);

    @Query("select new com.onlinelearning.model.dto.response.QuizUpdateResponseDto(q.quizId, q.quizName, q.lesson.lessonId, q.lesson.course.creator.userId) from Quiz q where q.quizId = :quizId and q.lesson.course.deleted = false")
    QuizUpdateResponseDto getQuizForManagerQuiz(int quizId);

    @Query("select exists (select 1 from Quiz q where q.quizName = :quizName and q.lesson.lessonId = :lessonId and q.quizId <> :quizId)")
    boolean isQuizExist(String quizName, int lessonId, int quizId);

    @Transactional
    @Modifying
    @Query("update Quiz q set q.quizName = :quizName, q.updatedAt = :updatedAt where q.quizId = :quizId")
    void updateQuiz(String quizName, Instant updatedAt, int quizId);

    @Query("SELECT q.lesson.course.creator.userId FROM Quiz q where q.quizId = :quizId and q.lesson.course.deleted = false")
    Long getCreatorId(int quizId);

    @Query("select q from Quiz q where q.quizId = :quizId and q.lesson.course.deleted = false")
    Quiz getQuizForManagerQuestion(int quizId);
}
