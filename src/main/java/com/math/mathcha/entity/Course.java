package com.math.mathcha.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int course_id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "original_price")
    private String original_price;
    @Column(name = "discount_price")
    private String discount_price;
    @Column(name = "is_deleted")
    private Boolean is_deleted = false;
    @Column(name = "status")
    private Boolean status = true;


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Chapter> chapters = new ArrayList<Chapter>();

//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<Quiz> quizs;
//
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<Enrollment> enrollments;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}

