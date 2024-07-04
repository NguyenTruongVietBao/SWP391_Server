package com.math.mathcha.repository.CourseRepository;

import com.math.mathcha.entity.Course;
import com.math.mathcha.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT c FROM Course c JOIN Enrollment e ON c.course_id = e.course.course_id JOIN Student s ON e.student.student_id = s.student_id WHERE s.student_id = :student_id")
    List<Course> findCoursesByStudentId(@Param("student_id") int student_id);
    @Query("SELECT DISTINCT c FROM Course c " +
            "JOIN Enrollment e ON c.course_id = e.course.course_id " +
            "JOIN Student s ON e.student.student_id = s.student_id " +
            "JOIN User u ON s.user.user_id = u.user_id " +
            "WHERE u.user_id = :user_id")
    List<Course> findCoursesBoughtByParent(@Param("user_id") int user_id);
    @Query("SELECT c FROM Course c WHERE c.course_id NOT IN (" +
            "SELECT DISTINCT c.course_id FROM Course c " +
            "JOIN Enrollment e ON c.course_id = e.course.course_id " +
            "JOIN Student s ON e.student.student_id = s.student_id " +
            "JOIN User u ON s.user.user_id = u.user_id " +
            "WHERE u.user_id = :user_id)")
    List<Course> findCoursesNotBoughtByParent(@Param("user_id") int user_id);

    @Query("SELECT c FROM Course c JOIN c.enrollments e JOIN e.payments p WHERE p.payment_date BETWEEN :startDate AND :endDate")
    List<Course> findCoursesByPaymentDates(String startDate, String endDate);



//    @Query("SELECT c FROM Course c WHERE c.course_id = :course_id")
//    Course findCourseById(@Param("course_id") int course_id);

}
