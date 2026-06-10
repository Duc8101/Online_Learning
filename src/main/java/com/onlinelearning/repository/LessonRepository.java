package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.LessonListForCourseDetailResponseDto;
import com.onlinelearning.model.dto.response.LessonListForLearnCourseResponseDto;
import com.onlinelearning.model.dto.response.ViewLessonResponseDto;
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

    @Query("select new com.onlinelearning.model.dto.response.LessonListForCourseDetailResponseDto(l.lessonId, l.lessonName, l.courseId) from Lesson l where l.courseId = :courseId")
    List<LessonListForCourseDetailResponseDto> getLessonsForCourseDetail(int courseId);

    @Query("""
            SELECT new com.onlinelearning.model.dto.response.LessonListForLearnCourseResponseDto
            (l.lessonId, l.lessonName,\s
                        CASE WHEN EXISTS\s
                                    (select 1 from Quiz q where q.lessonId = l.lessonId)
                        THEN TRUE ELSE FALSE END
            )\s
            FROM Lesson l
            WHERE l.courseId = :courseId
           \s""")
    List<LessonListForLearnCourseResponseDto> getLessonsForLearnCourse(int courseId);

    @Query("select l.courseId from Lesson l JOIN Course c on l.courseId = c.courseId where l.lessonId = :lessonId and c.deleted = false")
    Integer getCourseId(int lessonId);

    @Query("""
            SELECT new com.onlinelearning.model.dto.response.ViewLessonResponseDto(l.lessonId, l.lessonName)
            FROM Lesson l
            WHERE l.courseId = :courseId""")
    List<ViewLessonResponseDto> getLessonsForManagerAndViewLesson(int courseId);

    @Query("""
            SELECT EXISTS (
            SELECT 1 from Lesson l where l.lessonName = :lessonName and l.courseId = :courseId
            )""")
    boolean isLessonExist(String lessonName, int courseId);

    @Query("""
            SELECT EXISTS (
            SELECT 1 from Lesson l where l.lessonName = :lessonName and l.courseId = :courseId AND l.lessonId <> :lessonId
            )""")
    boolean isLessonExist(String lessonName, int courseId, int lessonId);

    @Transactional
    @Modifying
    @Query("update Lesson l set l.lessonName = :lessonName, l.updatedAt = :updatedAt where l.lessonId = :lessonId")
    void updateLesson(String lessonName, Instant updatedAt, int lessonId);

    @Query("""
            SELECT EXISTS (
            SELECT 1 from Lesson l join Course c on l.courseId = c.courseId where l.lessonId = :lessonId and c.deleted = false
            )""")
    boolean isLessonExist(int lessonId);
}
