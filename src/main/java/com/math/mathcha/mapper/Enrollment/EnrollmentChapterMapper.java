package com.math.mathcha.mapper.Enrollment;

import com.math.mathcha.dto.request.EnrollmentChapterDTO;
import com.math.mathcha.entity.Enrollments.EnrollmentChapter;

public class EnrollmentChapterMapper {
    public static EnrollmentChapterDTO mapToEnrollmentChapterDTO(EnrollmentChapter enrollmentChapter){
        EnrollmentChapterDTO enrollmentChapterDTO = new EnrollmentChapterDTO();
        enrollmentChapterDTO.setEnrollment_id(enrollmentChapter.getEnrollment().getEnrollment_id());
        enrollmentChapterDTO.setIs_complete(enrollmentChapter.getIs_complete());
        enrollmentChapterDTO.setEnrollmentChapter_id(enrollmentChapter.getEnrollmentChapter_id());
        enrollmentChapterDTO.setChapter_id(enrollmentChapter.getChapter().getChapter_id());
        return enrollmentChapterDTO;
    }

    public static EnrollmentChapter mapToEnrollmentChapter(EnrollmentChapterDTO enrollmentChapterDTO){
        EnrollmentChapter enrollmentChapter = new EnrollmentChapter();
        enrollmentChapter.setEnrollmentChapter_id(enrollmentChapterDTO.getEnrollmentChapter_id());
        enrollmentChapter.setIs_complete(enrollmentChapterDTO.getIs_complete());
        return enrollmentChapter;
    }
}
