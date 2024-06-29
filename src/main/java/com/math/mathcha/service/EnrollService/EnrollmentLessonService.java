package com.math.mathcha.service.EnrollService;


import com.math.mathcha.repository.EnrollRepository.EnrollmentLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentLessonService {
    @Autowired
    private EnrollmentLessonRepository enrollmentLessonRepository;

    public void markLessonComplete(int enrollmentLesson_id, int student_id) {
        enrollmentLessonRepository.markLessonComplete(enrollmentLesson_id, student_id);
    }
}