package com.math.mathcha.service.EnrollService;


import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.EnrollmentLessonDTO;
import com.math.mathcha.entity.Enrollment;
import com.math.mathcha.entity.Enrollments.EnrollmentLesson;
import com.math.mathcha.entity.Lesson;
import com.math.mathcha.mapper.Enrollment.EnrollmentLessonMapper;
import com.math.mathcha.mapper.EnrollmentMapper;
import com.math.mathcha.repository.EnrollRepository.EnrollmentLessonRepository;
import com.math.mathcha.repository.EnrollmentRepository;
import com.math.mathcha.repository.LessonRepository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EnrollmentLessonService {
    @Autowired
    private EnrollmentLessonRepository enrollmentLessonRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public EnrollmentLessonDTO createEnrollmentLesson(EnrollmentLessonDTO enrollmentLessonDTO) throws IdInvalidException {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentLessonDTO.getEnrollment_id())
                .orElseThrow(() -> new IdInvalidException("Enrollment not found"));

        Lesson lesson = lessonRepository.findById(enrollmentLessonDTO.getLesson_id())
                .orElseThrow(() -> new IdInvalidException("Course not found"));
        EnrollmentLesson enrollmentLesson = EnrollmentLessonMapper.mapToEnrollmentLesson(enrollmentLessonDTO);
        enrollmentLesson.setLesson(lesson);
        enrollmentLesson.setEnrollment(enrollment);
        enrollmentLesson.setIs_complete(enrollmentLessonDTO.getIs_complete());


        EnrollmentLesson savedEnrollment = enrollmentLessonRepository.save(enrollmentLesson);

        return EnrollmentLessonMapper.mapToEnrollmentLessonDTO(savedEnrollment);
    }
}