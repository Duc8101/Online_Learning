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

    @Query("""
            select new com.onlinelearning.model.dto.response.QuizListResponseDto(qz.quizId, qz.quizName, qz.lessonId)
            FROM Quiz qz
            where qz.lessonId = :lessonId and\s
            CASE WHEN EXISTS (
                        select 1 from Question qs where qs.quizId = qz.quizId
            ) then true else false end""")
    List<QuizListResponseDto> getQuizzesForTakeQuiz(int lessonId);

    @Query("""
            select q.lessonId as lessonId, c.courseId as courseId
            from Quiz q join Lesson l on q.lessonId = l.lessonId join Course c on c.courseId = l.courseId
            where q.quizId = :quizId and c.deleted = false""")
    Tuple getLessonIdAndCourseId(int quizId);

    @Query("""
            select new com.onlinelearning.model.dto.response.QuizListResponseDto(q.quizId, q.quizName, q.lessonId) FROM Quiz q
            where q.lessonId = :lessonId
            order by CASE WHEN q.updatedAt IS NULL THEN q.createdAt ELSE q.updatedAt END desc""")
    List<QuizListResponseDto>  getQuizzesForManagerQuiz(int lessonId);

    @Query("select exists (select 1 from Quiz q where q.quizName = :quizName and q.lessonId = :lessonId)")
    boolean isQuizExist(String quizName, int lessonId);

    @Query("""
            select new com.onlinelearning.model.dto.response.QuizUpdateResponseDto(q.quizId, q.quizName, q.lessonId, c.creatorId)
            from Quiz q join Lesson l on q.lessonId = l.lessonId join Course c on c.courseId = l.courseId
            where q.quizId = :quizId and c.deleted = false""")
    QuizUpdateResponseDto getQuizForManagerQuiz(int quizId);

    @Query("select exists (select 1 from Quiz q where q.quizName = :quizName and q.lessonId = :lessonId and q.quizId <> :quizId)")
    boolean isQuizExist(String quizName, int lessonId, int quizId);

    @Transactional
    @Modifying
    @Query("update Quiz q set q.quizName = :quizName, q.updatedAt = :updatedAt where q.quizId = :quizId")
    void updateQuiz(String quizName, Instant updatedAt, int quizId);

    @Query("SELECT c.creatorId from Quiz q join Lesson l on q.lessonId = l.lessonId join Course c on c.courseId = l.courseId where q.quizId = :quizId and c.deleted = false")
    Long getCreatorId(int quizId);

    @Query("select q from Quiz q join Lesson l on q.lessonId = l.lessonId join Course c on c.courseId = l.courseId where q.quizId = :quizId and c.deleted = false")
    Quiz getQuizForManagerQuestion(int quizId);
}
