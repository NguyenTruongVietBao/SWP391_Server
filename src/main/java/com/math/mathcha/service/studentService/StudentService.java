package com.math.mathcha.service.studentService;


import com.math.mathcha.dto.request.StudentDTO;


import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO, Integer user_id);

    StudentDTO getStudentById ( Integer student_id);

    List<StudentDTO> getStudentAll();

    StudentDTO updateStudent (StudentDTO studentDTO, Integer student_id);

    void deleteStudent (Integer student_id);

    List<StudentDTO> getStudentByUserId (int user_id);
}
