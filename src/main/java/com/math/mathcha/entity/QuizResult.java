package com.math.mathcha.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "quiz_result")
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "score")
    private int score;

    @Column(name = "date")
    private LocalDateTime attemptDate;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quizId;

    @Column(name = "enrollment_id")
    private Long enrollmentId;
}
