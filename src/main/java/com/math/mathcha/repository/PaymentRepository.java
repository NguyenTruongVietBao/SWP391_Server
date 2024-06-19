package com.math.mathcha.repository;

import com.math.mathcha.entity.Payment;
import com.math.mathcha.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
