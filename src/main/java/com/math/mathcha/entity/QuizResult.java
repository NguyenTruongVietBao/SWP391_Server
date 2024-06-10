package com.math.mathcha.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quiz_result")
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_result_id", nullable = false)
    private int quiz_result_id;

    @Column(name = "score")
    private String score;

    @Column(name = "attempt_date")
    private String attempt_date;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

//    @ManyToOne
//    @JoinColumn(name = "enrollment_id", nullable = false)
//    private Enrollment enrollment;
}
