package com.math.mathcha.entity;

import com.math.mathcha.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "phone",unique = true)
    private String phone;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "image")
    private String image;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "is_deleted")
    private Boolean is_deleted = false;
    @Enumerated(EnumType.STRING)
    Role role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)

    private List<Student> students;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "roles")
//    private Set<String> roles;
}