package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.StudentDTO;
import com.math.mathcha.entity.Student;


public class StudentMapper {
    public static StudentDTO mapToStudentDTO(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudent_id(student.getStudent_id());
        studentDTO.setFirst_name(student.getFirst_name());
        studentDTO.setLast_name(student.getLast_name());
        studentDTO.setPhone(student.getPhone());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setAddress(student.getAddress());
        studentDTO.setImage(student.getImage());
        studentDTO.setUsername(student.getUsername());
        studentDTO.setPassword(student.getPassword());
        studentDTO.setIs_deleted(student.getIs_deleted());
        return studentDTO;

    }
    public static Student mapToStudent(StudentDTO studentDTO){
        Student student = new Student();
        student.setStudent_id(studentDTO.getStudent_id());
        student.setFirst_name(studentDTO.getFirst_name());
        student.setLast_name(studentDTO.getLast_name());
        student.setPhone(studentDTO.getPhone());
        student.setEmail(studentDTO.getEmail());
        student.setAddress(studentDTO.getAddress());
        student.setImage(studentDTO.getImage());
        student.setUsername(studentDTO.getUsername());
        student.setPassword(studentDTO.getPassword());
        student.setIs_deleted(studentDTO.getIs_deleted());
        return student;

    }
}
