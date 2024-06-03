package com.math.mathcha.controller.StudentController;

import com.math.mathcha.dto.request.StudentDTO;
import com.math.mathcha.service.studentService.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;
123123
    232323
    @PostMapping
    public ResponseEntity<StudentDTO>  (@RequestBody StudentDTO studentDTO){
        StudentDTO savedStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping("{student_id}")
    public ResponseEntity<StudentDTO> getStudentById (@PathVariable("student_id") Integer student_id){
        StudentDTO studentDTO = studentService.getStudentById(student_id);
        return ResponseEntity.ok(studentDTO);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudentAll(){
        List<StudentDTO> student = studentService.getStudentAll();
        return ResponseEntity.ok(student);
    }

    @PutMapping("/update/{student_id}")
    public ResponseEntity<StudentDTO> updateStudent (@RequestBody StudentDTO updatedStudent, @PathVariable("student_id") Integer studentId){
        StudentDTO studentDTO = studentService.updateStudent(updatedStudent, studentId );
        return ResponseEntity.ok(studentDTO);
    }

    @DeleteMapping("{student_id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("student_id") Integer student_id){
        studentService.deleteStudent(student_id);
        return ResponseEntity.ok("Delete successfully !");
    }
}
