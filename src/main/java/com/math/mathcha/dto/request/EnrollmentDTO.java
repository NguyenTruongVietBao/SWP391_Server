package com.math.mathcha.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {

    private int enrollment_id;
    private Date enrollment_date;
    private StudentDTO student_id;
    private CourseDTO course_id;
}
