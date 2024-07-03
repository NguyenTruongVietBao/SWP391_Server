package com.math.mathcha.entity.Question;

import com.math.mathcha.entity.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int question_id; // sửa lại từ content sang id

    @Column(name = "content")
    private String content; // thêm trường content

    @Column(name = "title")
    private String title; // thêm trường title


    @Column(name = "correct_answer")
    private String correctAnswer;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionOption> questionOptions;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic; // sửa lại từ question sang Lesson
}
