package com.math.mathcha.service;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.EnrollmentDTO;
import com.math.mathcha.entity.*;
import com.math.mathcha.mapper.CourseMapper;
import com.math.mathcha.mapper.EnrollmentMapper;
import com.math.mathcha.repository.EnrollmentRepository;
import com.math.mathcha.repository.StudentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) throws IdInvalidException {
        Enrollment enrollment = EnrollmentMapper.mapToEnrollment(enrollmentDTO);
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return EnrollmentMapper.mapToEnrollmentDTO(savedEnrollment);

    }
}
