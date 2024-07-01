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

    public EnrollmentLessonDTO createEnrollmentLesson(int enrollment_id, int lesson_id) throws IdInvalidException {
        Enrollment enrollment = enrollmentRepository.findById(enrollment_id)
                .orElseThrow(() -> new IdInvalidException("Enrollment not found"));

        Lesson lesson = lessonRepository.findById(lesson_id)
                .orElseThrow(() -> new IdInvalidException("Lesson not found"));

        EnrollmentLesson enrollmentLesson = new EnrollmentLesson();
        enrollmentLesson.setEnrollment(enrollment);
        enrollmentLesson.setLesson(lesson);
        enrollmentLesson.setIs_complete(true);

        EnrollmentLesson savedEnrollment = enrollmentLessonRepository.save(enrollmentLesson);

        return EnrollmentLessonMapper.mapToEnrollmentLessonDTO(savedEnrollment);
    }
}