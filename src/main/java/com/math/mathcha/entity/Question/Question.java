package com.math.mathcha.entity.Question;

import com.math.mathcha.entity.Quiz;
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
    private Long question_id;

    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionOption> options;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne // Thêm quan hệ ManyToOne với Quiz
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz; // Thêm thuộc tính 'quiz' để liên kết với Quiz

}
