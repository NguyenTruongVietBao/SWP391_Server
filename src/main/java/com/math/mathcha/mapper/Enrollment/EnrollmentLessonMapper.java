package com.math.mathcha.mapper.Enrollment;

import com.math.mathcha.dto.request.EnrollmentLessonDTO;
import com.math.mathcha.entity.Enrollment;
import com.math.mathcha.entity.Enrollments.EnrollmentLesson;

public class EnrollmentLessonMapper {
    public static EnrollmentLessonDTO mapToEnrollmentLessonDTO(EnrollmentLesson enrollmentLesson){
        EnrollmentLessonDTO enrollmentLessonDTO = new EnrollmentLessonDTO();
        enrollmentLessonDTO.setEnrollment_id(enrollmentLesson.getEnrollment().getEnrollment_id());
        enrollmentLessonDTO.setIs_complete(enrollmentLesson.getIs_complete());
        enrollmentLessonDTO.setEnrollmentLesson_id(enrollmentLesson.getEnrollmentLesson_id());
        enrollmentLessonDTO.setLesson_id(enrollmentLesson.getLesson().getLesson_id());
        return enrollmentLessonDTO;
    }

    public static EnrollmentLesson mapToEnrollmentLesson(EnrollmentLessonDTO enrollmentLessonDTO){
        EnrollmentLesson enrollmentLesson = new EnrollmentLesson();
        enrollmentLesson.setEnrollmentLesson_id(enrollmentLessonDTO.getEnrollmentLesson_id());
        enrollmentLesson.setIs_complete(enrollmentLessonDTO.getIs_complete());
        return enrollmentLesson;
    }

}
