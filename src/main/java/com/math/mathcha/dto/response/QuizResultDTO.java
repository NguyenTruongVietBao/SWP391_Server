package com.math.mathcha.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizResultDTO {
    private List<Boolean> results;
    private int correctCount;
    private int totalQuestions;
    private boolean passed;
    private double scorePercentage;

    public QuizResultDTO(List<Boolean> results, int correctCount, int totalQuestions) {
        this.results = results;
        this.correctCount = correctCount;
        this.totalQuestions = totalQuestions;
        this.scorePercentage = (double) correctCount / totalQuestions * 100;
        this.passed = this.scorePercentage >=80;
    }
}
