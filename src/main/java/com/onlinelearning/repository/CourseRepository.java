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

import java.time.Instant;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(value = """
            SELECT new com.onlinelearning.model.dto.response.AllCoursesResponseDto(\
            c.courseId, c.courseName, c.image, c.categoryId, \
            c.creatorId, u.fullName, c.description, \
            CASE WHEN EXISTS (
                     select 1 from Lesson l where l.courseId = c.courseId
            ) then TRUE ELSE FALSE END,
            CASE WHEN :studentId IS NOT NULL AND EXISTS (
            SELECT 1 FROM EnrollCourse ec
             where ec.courseId = c.courseId and ec.studentId = :studentId
            ) THEN TRUE ELSE FALSE END)
            FROM Course c join User u on c.creatorId = u.userId
            where c.deleted = false
            and (:categoryId IS NULL or c.categoryId = :categoryId)
            order by\
             CASE WHEN :orderBy IS NULL THEN (CASE WHEN c.updatedAt IS NULL THEN c.createdAt ELSE c.updatedAt END)  END desc,
             CASE WHEN :orderBy = true THEN c.courseName END asc,
             CASE WHEN :orderBy = false THEN c.courseName END desc
            """,
            countQuery = "select count(c) FROM Course c where c.deleted = false and (:categoryId IS NULL or c.categoryId = :categoryId)")
    Page<AllCoursesResponseDto> getAllCourses(Integer categoryId, Boolean orderBy, Long studentId, Pageable pageable);

    @Query("""
            SELECT new com.onlinelearning.model.dto.response.CourseDetailResponseDto(\
            c.courseId, c.courseName, c.image, c.categoryId,\
            c.creatorId, u.fullName, c.description, \
            CASE WHEN EXISTS (
                     select 1 from Lesson l where l.courseId = c.courseId
            ) then TRUE ELSE FALSE END,
            CASE WHEN :studentId IS NOT NULL AND EXISTS (
            SELECT 1 FROM EnrollCourse ec where ec.courseId = c.courseId and ec.studentId = :studentId
            ) THEN TRUE ELSE FALSE END)
            FROM Course c join User u on c.creatorId = u.userId
            where c.deleted = false and c.courseId = :courseId
            """)
    CourseDetailResponseDto getCourseDetail(int courseId, Long studentId);

    @Query("select exists (select 1 from EnrollCourse ec where ec.courseId = c.courseId and ec.studentId = :studentId) from Course c where c.deleted = false and c.courseId = :courseId")
    Optional<Boolean> checkStudentEnrollCourse(int courseId, long studentId);

    @Query("""
            select new com.onlinelearning.model.dto.response.CheckLessonAndEnrollCourseExist(
                 CASE WHEN EXISTS (
                     select 1 from Lesson l where l.courseId = c.courseId
            ) then TRUE ELSE FALSE END,\s
                 CASE WHEN EXISTS (
                     select 1 from EnrollCourse ec where ec.courseId = c.courseId
            ) then TRUE ELSE FALSE END)
            from Course c
            where c.deleted = false and c.courseId = :courseId""")
    CheckLessonAndEnrollCourseExist checkLessonAndEnrollCourseExist(int courseId);

    @Transactional
    @Modifying
    @Query("update Course c set c.deleted = true where c.courseId = :courseId and c.deleted = false")
    void setCourseDeleted(int courseId);

    @Query(value = """
             SELECT new com.onlinelearning.model.dto.response.StudentOrTeacherCoursesResponseDto(\
             c.courseId, c.courseName, c.image, c.categoryId, \
             c.creatorId, u.fullName, c.description)\s
             FROM Course c join User u on c.creatorId = u.userId
             where c.deleted = false
             and EXISTS (
             SELECT 1 FROM EnrollCourse ec where ec.courseId = c.courseId and ec.studentId = :studentId
             )
            \s""",
            countQuery = """
                    SELECT COUNT (c.courseId) FROM Course c
                    where c.deleted = false
                    and EXISTS (
                    SELECT 1 FROM EnrollCourse ec where ec.courseId = c.courseId and ec.studentId = :studentId
                    )""")
    Page<StudentOrTeacherCoursesResponseDto> getStudentCourses(long studentId, Pageable pageable);

    @Query(value = """
            SELECT new com.onlinelearning.model.dto.response.StudentOrTeacherCoursesResponseDto(\
            c.courseId, c.courseName, c.image, c.categoryId, \
            c.creatorId, u.fullName, c.description)\s
            FROM Course c join User u on c.creatorId = u.userId
            where c.deleted = false
            and c.creatorId = :teacherId""",
            countQuery = """
                    SELECT COUNT (c.courseId) FROM Course c
                    where c.deleted = false
                    and c.creatorId = :teacherId""")
    Page<StudentOrTeacherCoursesResponseDto> getTeacherCourses(long teacherId, Pageable pageable);

    @Query("""
            SELECT EXISTS (
            SELECT 1 from Course c where c.courseName = :courseName and c.categoryId = :categoryId and c.deleted = false
            )""")
    boolean isCourseExist(String courseName, int categoryId);

    @Query("""
            SELECT new com.onlinelearning.model.dto.response.StudentOrTeacherCoursesResponseDto(\
            c.courseId, c.courseName, c.image, c.categoryId, \
            c.creatorId, u.fullName, c.description)\s
            FROM Course c join User u on c.creatorId = u.userId
            where c.deleted = false
            and c.categoryId = :teacherId and c.courseId = :courseId""")
    StudentOrTeacherCoursesResponseDto getTeacherCourse(long teacherId, int courseId);

    @Query("""
            SELECT EXISTS (
            SELECT 1 from Course c where c.courseName = :courseName and c.categoryId = :categoryId and c.deleted = false
            and c.courseId <> :courseId)""")
    boolean isCourseExist(String courseName, int categoryId, int courseId);

    @Transactional
    @Modifying
    @Query("""
            update Course c
            set c.courseName = :courseName
            , c.description = :description
            , c.categoryId = :categoryId
            , c.image = :image
            , c.updatedAt = :updatedAt
            where c.courseId = :courseId""")
    void updateCourseInfo(String courseName, String description, int categoryId, String image, Instant updatedAt, int courseId);

    @Query("""
            select new com.onlinelearning.model.dto.response.CheckLessonAndEnrollCourseExist(
              CASE WHEN EXISTS (
                     select 1 from Lesson l where l.courseId = c.courseId
            ) then TRUE ELSE FALSE END,\s
                 CASE WHEN EXISTS (
                     select 1 from EnrollCourse ec where ec.courseId = c.courseId
            ) then TRUE ELSE FALSE END)
            from Course c
            where c.deleted = false and c.courseId = :courseId and c.creatorId = :teacherId""")
    CheckLessonAndEnrollCourseExist checkLessonAndEnrollCourseExist(int courseId, long teacherId);

    @Query("""
            SELECT EXISTS (
            SELECT 1 from Course c where c.courseId = :courseId and c.creatorId = :teacherId and c.deleted = false
            )""")
    boolean checkCourseTeacherExist(int courseId, long teacherId);

    @Query("""
            SELECT EXISTS (
            SELECT 1 from Course c where c.courseId = :courseId and c.deleted = false
            )""")
    boolean isCourseExist(int courseId);
}
