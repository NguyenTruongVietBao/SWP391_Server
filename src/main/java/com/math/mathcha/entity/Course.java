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

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int course_id;

    private String title;

    private String description;

    private String image;

    private String original_price;

    private String discount_price;
}

