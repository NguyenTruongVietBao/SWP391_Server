package com.math.mathcha.repository.QuestionRepository;

import com.math.mathcha.entity.Question.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Integer> {
}