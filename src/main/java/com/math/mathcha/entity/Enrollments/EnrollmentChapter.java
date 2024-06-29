package com.math.mathcha.entity.Enrollments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.math.mathcha.entity.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EnrollmentChapter")
public class EnrollmentChapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollmentChapter_id")
    private int enrollmentChapter_id;

    @Column(name = "is_complete",columnDefinition = "TINYINT(1)")
    private Boolean is_complete;

    @ManyToOne
    @JoinColumn(name = "enrollment_id", nullable = false)
    @JsonIgnore
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false)
    @JsonIgnore
    private Chapter chapter;


}
