package com.math.mathcha.controller.StudentController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.StudentDTO;
import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.service.studentService.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/user/{user_id}")
    @PreAuthorize("hasRole('PARENT')")
    public ResponseEntity<StudentDTO> createStudent(@PathVariable("user_id") Integer user_id,
                                                @RequestBody StudentDTO studentDTO) throws IdInvalidException {
        StudentDTO savedStudent = studentService.createStudent(studentDTO, user_id);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping("{student_id}")
    @PreAuthorize("hasRole('PARENT')")
    public ResponseEntity<StudentDTO> getStudentById (@PathVariable("student_id") Integer student_id) throws IdInvalidException {
        StudentDTO studentDTO = studentService.getStudentById(student_id);
        return ResponseEntity.ok(studentDTO);
    }

    @GetMapping
    @PreAuthorize("hasRole('PARENT')")
    public ResponseEntity<List<StudentDTO>> getStudentAll(){
        List<StudentDTO> student = studentService.getStudentAll();
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{student_id}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentDTO> updateStudent (@RequestBody StudentDTO updatedStudent, @PathVariable("student_id") Integer studentId) throws IdInvalidException {
        StudentDTO studentDTO = studentService.updateStudent(updatedStudent, studentId );
        return ResponseEntity.ok(studentDTO);
    }

    @DeleteMapping("/{student_id}")
    @PreAuthorize("hasRole('PARENT')")
    public ResponseEntity<Void> deleteStudent(@PathVariable("student_id") Integer student_id) throws IdInvalidException {
        StudentDTO currentUser = this.studentService.getStudentById(student_id);
        this.studentService.deleteStudent(student_id);
        return ResponseEntity.ok(null);
    }
}
