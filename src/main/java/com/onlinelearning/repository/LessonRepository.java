package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.LessonListForCourseDetailResponseDto;
import com.onlinelearning.model.entity.Lesson;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    @Query("select new com.onlinelearning.model.dto.response.LessonListForCourseDetailResponseDto(l.lessonId, l.lessonName, l.course.courseId) from Lesson l where l.course.courseId = :courseId")
    List<LessonListForCourseDetailResponseDto> getLessonsForCourseDetail(int courseId);

    @Query("SELECT DISTINCT l FROM Lesson l " +
            "LEFT JOIN FETCH l.videos " +
            "LEFT JOIN FETCH l.pdfs " +
            "LEFT JOIN FETCH l.quizzes " +
            "WHERE l.course.courseId = :courseId")
    List<Lesson> getLessonsForLearnCourse(int courseId);

    @Query("select l.course.courseId from Lesson l where l.lessonId = :lessonId and l.course.deleted = false")
    Integer getCourseId(int lessonId);

    @Query("""
            SELECT DISTINCT l FROM Lesson l
            LEFT JOIN FETCH l.videos
            LEFT JOIN FETCH l.pdfs
            WHERE l.course.courseId = :courseId""")
    List<Lesson> getLessonsForManagerAndViewLesson(int courseId);

    @Query("SELECT EXISTS ("
            + "SELECT 1 from Lesson l where l.lessonName = :lessonName and l.course.courseId = :courseId" +
            ")")
    boolean isLessonExist(String lessonName, int courseId);

    @Query("SELECT EXISTS ("
            + "SELECT 1 from Lesson l where l.lessonName = :lessonName and l.course.courseId = :courseId AND l.lessonId <> :lessonId" +
            ")")
    boolean isLessonExist(String lessonName, int courseId, int lessonId);

    @Transactional
    @Modifying
    @Query("update Lesson l set l.lessonName = :lessonName, l.updatedAt = :updatedAt where l.lessonId = :lessonId")
    void updateLesson(String lessonName, Instant updatedAt, int lessonId);
}
