package com.math.mathcha.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvaluateQuizRequest {

    private QuizDTO quizDTO;



    public QuizDTO getQuizDTO() {
        return quizDTO;
    }

    public void setQuizDTO(QuizDTO quizDTO) {
        this.quizDTO = quizDTO;
    }
}

