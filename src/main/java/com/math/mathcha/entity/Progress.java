package com.math.mathcha.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date createDate;
    private int course_id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
}
