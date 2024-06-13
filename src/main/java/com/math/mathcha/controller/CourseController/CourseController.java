package com.math.mathcha.controller.CourseController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.CourseDTO;
import com.math.mathcha.service.courseService.CourseService;

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
public class CourseController {

    private CourseService courseService;
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse (@RequestBody CourseDTO courseDTO){
        CourseDTO savedCourse = courseService.createCourse(courseDTO);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @GetMapping("{course_id}")
    public ResponseEntity<CourseDTO> getCourseById (@PathVariable("course_id") Integer course_id) throws IdInvalidException {
        CourseDTO courseDTO = courseService.getCourseById(course_id);
        if (courseDTO == null) {
            throw new IdInvalidException("Course với id = " + course_id + " không tồn tại");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(courseDTO);
    }
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getCourseAll(){
        List<CourseDTO> courses = courseService.getCourseAll();
        return ResponseEntity.ok(courses);
    }
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @PutMapping("/{course_id}")
    public ResponseEntity<CourseDTO> updateCourse (@RequestBody CourseDTO updatedCourse, @PathVariable("course_id") Integer courseId){
        CourseDTO courseDTO = courseService.updateCourse(updatedCourse, courseId );
        return ResponseEntity.ok(courseDTO);
    }
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @DeleteMapping("{course_id}")
    public ResponseEntity<Void> deleteCourse( @PathVariable("course_id") Integer course_id) throws IdInvalidException {
        CourseDTO currentCourse = courseService.getCourseById(course_id);
        if (currentCourse == null) {
            throw new IdInvalidException("Course với id = " + course_id + " không tồn tại");
        }
        courseService.deleteCourse(course_id);
        return ResponseEntity.ok(null);
    }
}
