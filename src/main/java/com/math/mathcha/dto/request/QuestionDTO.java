package com.math.mathcha.dto.request;

import com.math.mathcha.entity.Question;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Question}
 */
@Value
public class QuestionDTO implements Serializable {
    int question_id;
    String question_title;
    String correct_answer;
}