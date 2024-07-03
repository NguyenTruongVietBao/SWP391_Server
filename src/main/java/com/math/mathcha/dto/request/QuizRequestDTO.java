package com.math.mathcha.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizRequestDTO {
    private int courseId;
    private int numOfQuestions;
    private int timeLimit;

    // Getters and setters
}

