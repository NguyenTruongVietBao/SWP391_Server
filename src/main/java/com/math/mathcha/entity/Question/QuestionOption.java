package com.math.mathcha.entity.Question;

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
@Table(name = "question_option")
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private int option_id;

    @Column(name = "option_question")
    private String option;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}
