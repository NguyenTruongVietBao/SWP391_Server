package com.math.mathcha.mapper.Enrollment;

import com.math.mathcha.dto.request.EnrollmentLessonDTO;
import com.math.mathcha.dto.request.EnrollmentTopicDTO;
import com.math.mathcha.entity.Enrollments.EnrollmentTopic;

public class EnrollmentTopicMapper {
    public static EnrollmentTopicDTO mapToEnrollmentTopicDTO(EnrollmentTopic enrollmentTopic){
        EnrollmentTopicDTO enrollmentTopicDTO = new EnrollmentTopicDTO();
        enrollmentTopicDTO.setEnrollment_id(enrollmentTopic.getEnrollment().getEnrollment_id());
        enrollmentTopicDTO.setIs_complete(enrollmentTopic.getIs_complete());
        enrollmentTopicDTO.setEnrollmentTopic_id(enrollmentTopic.getEnrollmentTopic_id());
        enrollmentTopicDTO.setTopic_id(enrollmentTopic.getTopic().getTopic_id());
        return enrollmentTopicDTO;
    }

    public static EnrollmentTopic mapToEnrollmentTopic(EnrollmentTopicDTO enrollmentTopicDTO){
        EnrollmentTopic enrollmentTopic = new EnrollmentTopic();
        enrollmentTopic.setEnrollmentTopic_id(enrollmentTopicDTO.getEnrollmentTopic_id());
        enrollmentTopic.setIs_complete(enrollmentTopicDTO.getIs_complete());
        return enrollmentTopic;
    }
}
