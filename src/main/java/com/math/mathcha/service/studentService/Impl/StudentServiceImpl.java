package com.math.mathcha.service.studentService.Impl;


import com.math.mathcha.dto.request.StudentDTO;

import com.math.mathcha.entity.Student;
import com.math.mathcha.entity.User;
import com.math.mathcha.mapper.ChapterMapper;
import com.math.mathcha.mapper.StudentMapper;
import com.math.mathcha.repository.StudentRepository.StudentRepository;

import com.math.mathcha.repository.UserRepository.UserRepository;
import com.math.mathcha.service.studentService.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private UserRepository userRepository;

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO, Integer user_id) {
        Student student = StudentMapper.mapToStudent(studentDTO);
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User: "+user_id+" not found"));
        student.setUser(user);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDTO(savedStudent);
    }

    @Override
    public StudentDTO getStudentById(Integer student_id) {
        Optional<Student> student = studentRepository.findById(student_id);
        if (student.isPresent()) {
            return StudentMapper.mapToStudentDTO(student.get());
        }
        return null;
    }

    @Override
    public List<StudentDTO> getStudentAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(
                (student) -> StudentMapper.mapToStudentDTO(student)).collect(Collectors.toList()
        );
    }

    @Override
    public StudentDTO updateStudent(StudentDTO updateStudent, Integer student_id) {
        Student student = studentRepository.findById(student_id)
                .orElseThrow(()-> new RuntimeException("Student "+student_id+" not found"));
        student.setFirst_name(updateStudent.getFirst_name());
        student.setLast_name(updateStudent.getLast_name());
        student.setPhone(updateStudent.getPhone());
        student.setEmail(updateStudent.getEmail());
        student.setAddress(updateStudent.getAddress());
        student.setImage(updateStudent.getImage());
        student.setUsername(updateStudent.getUsername());
        student.setPassword(updateStudent.getPassword());
        student.setIs_deleted(updateStudent.getIs_deleted());
        Student updateStudentObj = studentRepository.save(student);
        return StudentMapper.mapToStudentDTO(updateStudentObj);
    }

    @Override
    public void deleteStudent(Integer student_id) {
        Student student = studentRepository.findById(student_id)
                .orElseThrow(() -> new RuntimeException("Not exits"+student_id));
        studentRepository.deleteById(student_id);
    }

    @Override
    public List<StudentDTO> getStudentByUserId(int user_id) {
        List<Student> students = studentRepository.findStudentByUserId(user_id);
        return students.stream().map(
                (student) -> StudentMapper.mapToStudentDTO(student)).collect(Collectors.toList()
        );
    }
}
