package com.math.mathcha.dto.request;

public class GenerateQuizRequest {

    private int numberOfQuestions;
    private int timeLimit;

    // Các getters và setters

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
}

