package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.AllCoursesResponseDto;
import com.onlinelearning.model.dto.response.CheckLessonAndEnrollCourseExist;
import com.onlinelearning.model.dto.response.CourseDetailResponseDto;
import com.onlinelearning.model.dto.response.StudentOrTeacherCoursesResponseDto;
import com.onlinelearning.model.entity.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(value = """
            SELECT new com.onlinelearning.model.dto.response.AllCoursesResponseDto(\
            c.courseId, c.courseName, c.image, c.category.categoryId, \
            c.creator.userId, c.creator.fullName, c.description, \
            CASE WHEN size(c.lessons) > 0 then TRUE ELSE FALSE END,
            CASE WHEN :studentId IS NOT NULL AND EXISTS (
            SELECT 1 FROM EnrollCourse ec where ec.course = c and ec.student.userId = :studentId
            ) THEN TRUE ELSE FALSE END)
            FROM Course c
            where c.deleted = false
            and (:categoryId IS NULL or c.category.categoryId = :categoryId)
            order by\
             CASE WHEN :orderBy IS NULL THEN (CASE WHEN c.updatedAt IS NULL THEN c.createdAt ELSE c.updatedAt END)  END desc,
             CASE WHEN :orderBy = true THEN c.courseName END asc,
             CASE WHEN :orderBy = false THEN c.courseName END desc
            """,
            countQuery = "select count(c) FROM Course c where c.deleted = false and (:categoryId IS NULL or c.category.categoryId = :categoryId)")
    Page<AllCoursesResponseDto> getAllCourses(Integer categoryId, Boolean orderBy, Long studentId, Pageable pageable);

    @Query("SELECT new com.onlinelearning.model.dto.response.CourseDetailResponseDto("
            + "c.courseId, c.courseName, c.image, c.category.categoryId,"
            + "c.creator.userId, c.creator.fullName, c.description, "
            + "CASE WHEN EXISTS (select 1 from Lesson l where l.course = c) THEN TRUE ELSE FALSE END,\n"
            + "CASE WHEN :studentId IS NOT NULL AND EXISTS (\n"
            + "SELECT 1 FROM EnrollCourse ec where ec.course = c and ec.student.userId = :studentId\n"
            + ") THEN TRUE ELSE FALSE END)\n"
            + "FROM Course c\n"
            + "where c.deleted = false and c.courseId = :courseId\n")
    CourseDetailResponseDto getCourseDetail(int courseId, Long studentId);

    @Query("select c from Course c where c.courseId = :courseId and c.deleted = false")
    Course getCourseByCourseId(int courseId);

    @Query("select exists (select 1 from EnrollCourse ec where ec.course = c and ec.student.userId = :studentId) from Course c where c.deleted = false and c.courseId = :courseId")
    Optional<Boolean> checkStudentEnrollCourse(int courseId, long studentId);

    @Query("select new com.onlinelearning.model.dto.response.CheckLessonAndEnrollCourseExist(case when size(c.lessons) > 0 then true else false end, case when size(c.enrollCourses) > 0 then true else false end) from Course c where c.deleted = false and c.courseId = :courseId")
    CheckLessonAndEnrollCourseExist checkLessonAndEnrollCourseExist(int courseId);

    @Transactional
    @Modifying
    @Query("update Course c set c.deleted = true where c.courseId = :courseId and c.deleted = false")
    void setCourseDeleted(int courseId);

    @Query(value = """
            SELECT new com.onlinelearning.model.dto.response.StudentOrTeacherCoursesResponseDto(\
            c.courseId, c.courseName, c.image, c.category.categoryId, \
            c.creator.userId, c.creator.fullName, c.description) FROM Course c
            where c.deleted = false
            and EXISTS (
            SELECT 1 FROM EnrollCourse ec where ec.course = c and ec.student.userId = :studentId
            )
            """,
            countQuery = "SELECT COUNT (c.courseId) FROM Course c\n"
                    + "where c.deleted = false\n"
                    + "and EXISTS (\n"
                    + "SELECT 1 FROM EnrollCourse ec where ec.course = c and ec.student.userId = :studentId\n"
                    + ")")
    Page<StudentOrTeacherCoursesResponseDto> getStudentCourses(long studentId, Pageable pageable);
}
