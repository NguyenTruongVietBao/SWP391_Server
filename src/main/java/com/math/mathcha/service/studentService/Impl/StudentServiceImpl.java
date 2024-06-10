package com.math.mathcha.service.studentService.Impl;


import com.math.mathcha.dto.request.StudentDTO;

import com.math.mathcha.entity.Student;
import com.math.mathcha.mapper.StudentMapper;
import com.math.mathcha.repository.StudentRepository.StudentRepository;

import com.math.mathcha.service.studentService.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
//    private StudentRepository studentRepository;
//
//    @Override
//    public StudentDTO createStudent(StudentDTO studentDTO) {
//        Student student = StudentMapper.mapToStudent(studentDTO);
//        Student savedStudent= studentRepository.save(student);
//        return StudentMapper.mapToStudentDTO(savedStudent);
//    }
//
//    @Override
//    public StudentDTO getStudentById(Integer student_id) {
//        Student student = studentRepository.findById(student_id)
//                .orElseThrow(() -> new RuntimeException("Student "+student_id+" not found"));
//        return StudentMapper.mapToStudentDTO(student);
//    }
//
//    @Override
//    public List<StudentDTO> getStudentAll() {
//        List<Student> students = studentRepository.findAll();
//        return students.stream().map(
//                (student) -> StudentMapper.mapToStudentDTO(student)).collect(Collectors.toList()
//        );
//    }
//
//    @Override
//    public StudentDTO updateStudent(StudentDTO updateStudent, Integer student_id) {
//        Student student = studentRepository.findById(student_id)
//                .orElseThrow(()-> new RuntimeException("Student "+student_id+" not found"));
//        student.setFirst_name(updateStudent.getFirst_name());
//        student.setLast_name(updateStudent.getLast_name());
//        student.setPhone(updateStudent.getPhone());
//        student.setEmail(updateStudent.getEmail());
//        student.setAddress(updateStudent.getAddress());
//        student.setImage(updateStudent.getImage());
//        student.setUsername(updateStudent.getUsername());
//        student.setPassword(updateStudent.getPassword());
//        student.setIs_deleted(updateStudent.getIs_deleted());
//        student.setUser_id(updateStudent.getUser_id());
//        Student updateStudentObj = studentRepository.save(student);
//        return StudentMapper.mapToStudentDTO(updateStudentObj);
//    }
//
//    @Override
//    public void deleteStudent(Integer student_id) {
//        Student student = studentRepository.findById(student_id)
//                .orElseThrow(() -> new RuntimeException("Not exits"+student_id));
//        studentRepository.deleteById(student_id);
//    }
}
