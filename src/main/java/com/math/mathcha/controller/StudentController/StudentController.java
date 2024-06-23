package com.math.mathcha.controller.StudentController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.CourseDTO;
import com.math.mathcha.dto.request.StudentDTO;
import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.service.courseService.CourseService;
import com.math.mathcha.service.studentService.StudentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@SecurityRequirement(name = "api")
public class StudentController {
    private StudentService studentService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CourseService courseService;

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
    @PreAuthorize("hasRole('PARENT')")
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
    @GetMapping("/{student_id}/courses")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<CourseDTO>> getCoursesByStudentId(@PathVariable("student_id") int student_id) throws IdInvalidException {
        List<CourseDTO> courses = courseService.getCourseByStudentId(student_id);
        return ResponseEntity.ok(courses);

    }
}
