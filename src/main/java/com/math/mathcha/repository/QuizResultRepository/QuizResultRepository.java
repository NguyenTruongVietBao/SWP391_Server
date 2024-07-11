package com.math.mathcha.repository.QuizResultRepository;

import com.math.mathcha.entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Integer> {
    @Query("SELECT q FROM QuizResult q WHERE q.enrollment.enrollment_id = :enrollment_id")
    List<QuizResult> findByEnrollment_id(@Param(value = "enrollment_id") int enrollment_id);
}



