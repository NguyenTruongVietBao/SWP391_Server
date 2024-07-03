package com.math.mathcha.repository.QuizResultRepository;

import com.math.mathcha.entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Integer> {
}



