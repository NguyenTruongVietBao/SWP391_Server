package com.math.primarySchoolMath.entity;

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

public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int lesson_id;

    private String title;

    private int number;

    private String document;

    private String video_url;

    private int topic_id;
}
