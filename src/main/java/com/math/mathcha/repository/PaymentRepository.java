package com.math.mathcha.repository;

import com.math.mathcha.entity.Payment;
import com.math.mathcha.entity.Student;
import com.math.mathcha.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    @Query("SELECT t FROM Payment t WHERE t.user.user_id = :user_id")
    List<Payment> findPaymentByUserId(@Param(value = "user_id") int user_id);
    @Query("SELECT p FROM Payment p WHERE p.enrollment.course.course_id = :course_id")
    List<Payment> findPaymentByCourseId(@Param(value = "course_id") int course_id);
    @Query("SELECT p FROM Payment p WHERE p.payment_date LIKE CONCAT(:date, '%')")
    List<Payment> findPaymentsByDate(@Param("date") String date);
}
