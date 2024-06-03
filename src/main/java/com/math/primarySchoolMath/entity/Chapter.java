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

public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int chapter_id;

    private String title;

    private int number;

    private int course_id;

}
