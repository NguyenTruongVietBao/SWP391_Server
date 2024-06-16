package com.math.mathcha.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class QuizDTO {
    private List<QuestionDTO> questions;
    private List<String> userAnswer;
}
