package com.math.mathcha.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizResult_id")
    private int quizResult_id;

    @Column(name = "quiz_name", nullable = false)
    private String quiz_name;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "date",nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "enrollment_id", nullable = false)
    @JsonIgnore
    private Enrollment enrollment;
}
