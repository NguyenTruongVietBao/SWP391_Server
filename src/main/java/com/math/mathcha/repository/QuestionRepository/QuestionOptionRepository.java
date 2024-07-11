package com.math.mathcha.repository.QuestionRepository;

import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.entity.Question.QuestionOption;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM QuestionOption qo WHERE qo.question = :question")
    void deleteByQuestion(@Param("question") Question question);
}