package com.math.mathcha.entity;


import com.math.mathcha.dto.request.QuestionDTO;
import jakarta.persistence.Entity;

import java.util.List;
@Entity
public class Quiz {
    private List<QuestionDTO> questions;
    private int timeLimit; // Thời gian làm bài
    private int numberOfQuestions; // Số câu hỏi của bài kiểm tra

    // Getters và Setters
    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}

