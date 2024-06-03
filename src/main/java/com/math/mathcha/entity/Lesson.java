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
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private int lesson_id;
    @Column(name = "title")
    private String title;
    @Column(name = "number")
    private int number;
    @Column(name = "document")
    private String document;
    @Column(name = "video_url")
    private String video_url;
    @Column(name = "topic_id")
    private int topic_id;
}
