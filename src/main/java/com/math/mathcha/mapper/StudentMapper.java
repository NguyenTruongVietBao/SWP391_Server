package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.StudentDTO;
import com.math.mathcha.entity.Student;

public class StudentMapper {
    public static StudentDTO mapToStudentDTO(Student student){
        return new StudentDTO(
                student.getStudent_id(),
                student.getFirst_name(),
                student.getLast_name(),
                student.getPhone(),
                student.getEmail(),
                student.getAddress(),
                student.getImage(),
                student.getUsername(),
                student.getPassword(),
                student.getIs_deleted(),
                student.getUser_id()
        );

    }
    public static Student mapToStudent(StudentDTO studentDTO){
        return new Student(
                studentDTO.getStudent_id(),
                studentDTO.getFirst_name(),
                studentDTO.getLast_name(),
                studentDTO.getPhone(),
                studentDTO.getEmail(),
                studentDTO.getAddress(),
                studentDTO.getImage(),
                studentDTO.getUsername(),
                studentDTO.getPassword(),
                studentDTO.getIs_deleted(),
                studentDTO.getUser_id()
        );

    }
}
