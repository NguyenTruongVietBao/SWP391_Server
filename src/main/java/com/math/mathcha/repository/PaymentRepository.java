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
}
