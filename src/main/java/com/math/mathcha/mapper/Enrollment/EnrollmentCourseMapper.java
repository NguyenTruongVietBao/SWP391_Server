package com.math.mathcha.mapper.Enrollment;

import com.math.mathcha.dto.request.EnrollmentCourseDTO;
import com.math.mathcha.dto.request.EnrollmentLessonDTO;
import com.math.mathcha.entity.Enrollments.EnrollmentCourse;

public class EnrollmentCourseMapper {
    public static EnrollmentCourseDTO mapToEnrollmentCourseDTO(EnrollmentCourse enrollmentCourse){
        EnrollmentCourseDTO enrollmentCourseDTO = new EnrollmentCourseDTO();
        enrollmentCourseDTO.setEnrollment_id(enrollmentCourse.getEnrollment().getEnrollment_id());
        enrollmentCourseDTO.setIs_complete(enrollmentCourse.getIs_complete());
        enrollmentCourseDTO.setEnrollmentCourse_id(enrollmentCourse.getEnrollmentCourse_id());
        enrollmentCourseDTO.setCourse_id(enrollmentCourse.getCourse().getCourse_id());
        return enrollmentCourseDTO;
    }

    public static EnrollmentCourse mapToEnrollmentCourse(EnrollmentCourseDTO enrollmentCourseDTO){
        EnrollmentCourse enrollmentLesson = new EnrollmentCourse();
        enrollmentLesson.setEnrollmentCourse_id(enrollmentCourseDTO.getEnrollmentCourse_id());
        enrollmentLesson.setIs_complete(enrollmentCourseDTO.getIs_complete());
        return enrollmentLesson;
    }
}
