package com.math.mathcha.service.EnrollService;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.EnrollmentCourseDTO;
import com.math.mathcha.entity.Course;
import com.math.mathcha.entity.Enrollment;
import com.math.mathcha.entity.Enrollments.EnrollmentCourse;

import com.math.mathcha.mapper.Enrollment.EnrollmentCourseMapper;
import com.math.mathcha.mapper.Enrollment.EnrollmentLessonMapper;
import com.math.mathcha.repository.CourseRepository.CourseRepository;
import com.math.mathcha.repository.EnrollRepository.EnrollmentCourseRepository;
import com.math.mathcha.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentCourseService {
    @Autowired
    private EnrollmentCourseRepository enrollmentCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public EnrollmentCourseDTO createEnrollmentCourse(int enrollment_id, int course_id) throws IdInvalidException {
        Enrollment enrollment = enrollmentRepository.findById(enrollment_id)
                .orElseThrow(() -> new IdInvalidException("Enrollment not found"));

        Course course = courseRepository.findById(course_id)
                .orElseThrow(() -> new IdInvalidException("Course not found"));

        EnrollmentCourse enrollmentCourse = new EnrollmentCourse();
        enrollmentCourse.setEnrollment(enrollment);
        enrollmentCourse.setCourse(course);
        enrollmentCourse.setIs_complete(true);

        EnrollmentCourse savedEnrollment = enrollmentCourseRepository.save(enrollmentCourse);

        return EnrollmentCourseMapper.mapToEnrollmentCourseDTO(savedEnrollment);
    }

    public Boolean getIsCompleteByEnrollmentIdAndCourseId(int enrollmentId, int lessonId) {
        return enrollmentCourseRepository.findIsCompleteByEnrollmentIdAndCourseId(enrollmentId, lessonId);
    }
}
