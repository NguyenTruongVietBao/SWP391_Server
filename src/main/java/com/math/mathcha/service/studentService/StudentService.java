package com.math.mathcha.service.studentService;


import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO, Integer user_id) throws IdInvalidException;

    StudentDTO getStudentById ( Integer student_id) throws IdInvalidException;

    List<StudentDTO> getStudentAll();

    StudentDTO updateStudent (StudentDTO studentDTO, Integer student_id);

    void deleteStudent (Integer student_id) throws IdInvalidException;

    List<StudentDTO> getStudentByUserId (int user_id);

    boolean isUsernameExist (String username);

}
