package com.math.mathcha.repository;

import com.math.mathcha.entity.Payment;
import com.math.mathcha.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("SELECT t FROM Payment t WHERE t.user.user_id = :user_id")
    List<Payment> findPaymentByUserId(@Param(value = "user_id") int user_id);

    @Query("SELECT p FROM Payment p WHERE p.enrollment.course.course_id = :course_id")
    List<Payment> findPaymentByCourseId(@Param(value = "course_id") int course_id);

    @Query("SELECT p FROM Payment p WHERE p.payment_date BETWEEN :startDate AND :endDate")
    List<Payment> findPaymentsBetweenDates(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query("SELECT COUNT(DISTINCT p.user.user_id) FROM Payment p WHERE p.payment_date BETWEEN :startDate AND :endDate")
    int countDistinctUsersByPaymentDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query("SELECT COUNT(DISTINCT p.user.user_id) FROM Payment p WHERE p.enrollment.course.course_id = :course_id AND p.payment_date BETWEEN :startDate AND :endDate")
    int countTotalUsersPurchasedCourseOnDate(@Param("course_id") int courseId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
