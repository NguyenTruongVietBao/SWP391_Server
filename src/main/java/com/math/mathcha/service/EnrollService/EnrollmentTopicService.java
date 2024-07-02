package com.math.mathcha.service.EnrollService;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.EnrollmentTopicDTO;
import com.math.mathcha.entity.Enrollment;
import com.math.mathcha.entity.Enrollments.EnrollmentTopic;
import com.math.mathcha.entity.Topic;
import com.math.mathcha.mapper.Enrollment.EnrollmentTopicMapper;
import com.math.mathcha.repository.EnrollRepository.EnrollmentTopicRepository;
import com.math.mathcha.repository.EnrollmentRepository;
import com.math.mathcha.repository.TopicRepository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentTopicService {
    @Autowired
    private EnrollmentTopicRepository enrollmentTopicRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public EnrollmentTopicDTO createEnrollmentTopic(int enrollment_id, int lesson_id) throws IdInvalidException {
        Enrollment enrollment = enrollmentRepository.findById(enrollment_id)
                .orElseThrow(() -> new IdInvalidException("Enrollment not found"));

        Topic topic = topicRepository.findById(lesson_id)
                .orElseThrow(() -> new IdInvalidException("Lesson not found"));

        EnrollmentTopic enrollmentTopic = new EnrollmentTopic();
        enrollmentTopic.setEnrollment(enrollment);
        enrollmentTopic.setTopic(topic);
        enrollmentTopic.setIs_complete(true);

        EnrollmentTopic savedEnrollment = enrollmentTopicRepository.save(enrollmentTopic);

        return EnrollmentTopicMapper.mapToEnrollmentTopicDTO(savedEnrollment);
    }

    public Boolean getIsCompleteByEnrollmentIdAndTopicId(int enrollmentId, int lessonId) {
        return enrollmentTopicRepository.findIsCompleteByEnrollmentIdAndTopicId(enrollmentId, lessonId);
    }
}
