package com.math.mathcha.controller.CourseController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.dto.request.CourseDTO;
import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.service.courseService.CourseService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/course")
@SecurityRequirement(name = "api")
public class CourseController {

    private CourseService courseService;

    @PostMapping
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<CourseDTO> createCourse (@RequestBody CourseDTO courseDTO){
        CourseDTO savedCourse = courseService.createCourse(courseDTO);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping("{course_id}")
    public ResponseEntity<CourseDTO> getCourseById (@PathVariable("course_id") Integer course_id) throws IdInvalidException {
        CourseDTO courseDTO = courseService.getCourseById(course_id);
        return ResponseEntity.status(HttpStatus.OK).body(courseDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<List<CourseDTO>> getCourseAll(){
        List<CourseDTO> courses = courseService.getCourseAll();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{course_id}")
    @PreAuthorize("hasAnyRole('CONTENT_MANAGER', 'MANAGER')")
    public ResponseEntity<CourseDTO> updateCourse (@RequestBody CourseDTO updatedCourse, @PathVariable("course_id") Integer courseId){
        CourseDTO courseDTO = courseService.updateCourse(updatedCourse, courseId );
        return ResponseEntity.ok(courseDTO);
    }

    @DeleteMapping("{course_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<Void> deleteCourse( @PathVariable("course_id") Integer course_id) throws IdInvalidException {
        CourseDTO currentCourse = courseService.getCourseById(course_id);
        courseService.deleteCourse(course_id);
        return ResponseEntity.ok(null);
    }
}
