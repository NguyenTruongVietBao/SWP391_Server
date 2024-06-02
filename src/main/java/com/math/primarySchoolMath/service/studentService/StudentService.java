package com.math.primarySchoolMath.service.studentService;


import com.math.primarySchoolMath.dto.request.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO getStudentById ( Integer student_id);

    List<StudentDTO> getStudentAll();

    StudentDTO updateStudent (StudentDTO studentDTO, Integer student_id);

    void deleteStudent (Integer student_id);
}
