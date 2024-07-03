package com.math.mathcha.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.enums.QuizType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long quiz_id;

    @Column(name = "num_of_questions")
    private int numOfQuestions;

    @Column(name = "time_limit")
    private int timeLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = "quiz_type")
    private QuizType quizType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "course_id",  nullable = true)
    private Course course;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "chapter_id", nullable = true)
    private Chapter chapter;


    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = true)
    @JsonIgnore
    private Topic topic;

    @ManyToMany
    @JoinTable(
            joinColumns =  @JoinColumn(name = "quiz_Id"),
            inverseJoinColumns = @JoinColumn(name = "questions_id")
    )
    private List<Question> questions;

}



//@Getter
//@Setter
//@Entity
//@Table(name = "quiz")
//public class Quiz {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "quiz_id")
//    private Long quiz_id;
//
//    @Column(name = "num_of_questions")
//    private int numOfQuestions;
//
//    @Column(name = "time_limit")
//    private int timeLimit;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "quiz_type")
//    private QuizType quizType;
//
//    @ManyToOne
//    @JoinColumn(name = "course_id", nullable = true)
//    private Course course_id;
//
//    @ManyToOne
//    @JoinColumn(name = "chapter_id", nullable = true)
//    private Chapter chapter_id;
//
//    @ManyToOne
//    @JoinColumn(name = "topic_id", nullable = true)
//    private Topic topic_id;
//
//    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Question> questions;
//}

