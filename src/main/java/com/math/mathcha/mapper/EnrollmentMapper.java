package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.CourseDTO;
import com.math.mathcha.dto.request.EnrollmentDTO;
import com.math.mathcha.dto.request.LessonDTO;
import com.math.mathcha.dto.request.StudentDTO;
import com.math.mathcha.entity.Enrollment;
import com.math.mathcha.entity.Lesson;

public class EnrollmentMapper {
    public static EnrollmentDTO mapToEnrollmentDTO(Enrollment enrollment){
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.setEnrollment_id(enrollment.getEnrollment_id());
        enrollmentDTO.setEnrollment_date(enrollment.getEnrollment_date());
        enrollmentDTO.setStudent_id(enrollment.getStudent().getStudent_id());
        enrollmentDTO.setCourse_id(enrollment.getCourse().getCourse_id());
        return enrollmentDTO;
    }

    public static Enrollment mapToEnrollment(EnrollmentDTO enrollmentDTO){
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollment_id(enrollmentDTO.getEnrollment_id());
        enrollment.setEnrollment_date(enrollmentDTO.getEnrollment_date());
        return enrollment;
    }
}
