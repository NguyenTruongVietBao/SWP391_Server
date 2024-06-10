package com.math.mathcha.dto.request;

import com.math.mathcha.entity.*;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.math.mathcha.entity.Quiz}
 */
@Value
public class QuizDTO implements Serializable {
    int quiz_id;
    String name;
    int number;
    int min_pass_score;
    Boolean is_pass_required;
    @NotNull
    List<QuizResult> quizResults;
    @NotNull
    List<Question> questions;
    @NotNull
    Course course;
    @NotNull
    Chapter chapter;
    @NotNull
    Topic topic;
}