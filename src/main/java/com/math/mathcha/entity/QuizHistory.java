package com.math.mathcha.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class QuizHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long quizId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // Getters and Setters
}
