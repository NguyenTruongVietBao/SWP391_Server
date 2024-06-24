package com.math.mathcha.service;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.EnrollmentDTO;
import com.math.mathcha.entity.*;
import com.math.mathcha.mapper.CourseMapper;
import com.math.mathcha.mapper.EnrollmentMapper;
import com.math.mathcha.repository.CourseRepository.CourseRepository;
import com.math.mathcha.repository.EnrollmentRepository;
import com.math.mathcha.repository.StudentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) throws IdInvalidException {
        Student student = studentRepository.findById(enrollmentDTO.getStudent_id())
                .orElseThrow(() -> new IdInvalidException("Student not found"));

        Course course = courseRepository.findById(enrollmentDTO.getCourse_id())
                .orElseThrow(() -> new IdInvalidException("Course not found"));
        Enrollment enrollment = EnrollmentMapper.mapToEnrollment(enrollmentDTO);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollment_date(new Date());


        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return EnrollmentMapper.mapToEnrollmentDTO(savedEnrollment);
    }
    public List<Course> getCoursesByStudentId(int studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        return enrollments.stream().map(Enrollment::getCourse).collect(Collectors.toList());
    }
}
