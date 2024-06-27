package com.math.mathcha.repository.QuizHistoryRepository;

import com.math.mathcha.entity.QuizHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizHistoryRepository extends JpaRepository<QuizHistory, Long> {
}



