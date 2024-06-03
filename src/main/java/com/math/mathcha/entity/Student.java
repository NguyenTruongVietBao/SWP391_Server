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

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int student_id;

    private String first_name;

    private String last_name;

    private String phone;

    private String email;

    private String address;

    private String image;

    private String username;

    private String password;

    private Boolean is_deleted;

    private int user_id;

}