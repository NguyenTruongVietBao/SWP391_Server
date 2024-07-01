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
    @Column(name = "id")
    private Long id;

    @Column(name = "option_text")
    private String optionText;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}


