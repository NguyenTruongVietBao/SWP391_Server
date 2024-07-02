package com.math.mathcha.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentCourseDTO {
    private int enrollmentCourse_id;
    private Boolean is_complete = true;
    private int enrollment_id;
    private int course_id;
}
