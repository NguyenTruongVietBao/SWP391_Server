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
@Table(name = "chapter")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_id")
    private int chapter_id;
    @Column(name = "title")
    private String title;
    @Column(name = "number")
    private int number;
    @Column(name = "course_id")
    private int course_id;

}
