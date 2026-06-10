package com.onlinelearning.repository;

import com.onlinelearning.model.entity.EnrollCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollCourseRepository extends JpaRepository<EnrollCourse, String> {

    @Query("""
            select exists (
            SELECT 1 FROM EnrollCourse ec where ec.courseId = :courseId and ec.studentId = :studentId
            )""")
    boolean checkStudentEnrollCourse(int courseId, long studentId);

    @Query("""
            select exists (
            SELECT 1 FROM EnrollCourse ec where ec.courseId = :courseId
            )""")
    boolean isEnrollCourseExist(int courseId);
}
