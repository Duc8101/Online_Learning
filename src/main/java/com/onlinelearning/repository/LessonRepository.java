package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.LessonListForCourseDetailResponseDto;
import com.onlinelearning.model.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
