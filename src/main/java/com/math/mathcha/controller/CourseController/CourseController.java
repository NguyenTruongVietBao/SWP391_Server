package com.math.mathcha.controller.CourseController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.CourseDTO;
import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.service.courseService.CourseService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @PostMapping("/user/{user_id}/{category_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<CourseDTO> createCourse(@PathVariable("user_id") Integer user_id,@PathVariable("category_id") Integer category_id,
                                                @RequestBody CourseDTO courseDTO) throws IdInvalidException {
        CourseDTO savedCourse = courseService.createCourse(courseDTO, user_id, category_id);
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
    @PreAuthorize("hasAnyRole('CONTENT_MANAGER', 'MANAGER')")
    public ResponseEntity<Void> deleteCourse( @PathVariable("course_id") Integer course_id) throws IdInvalidException {
        CourseDTO currentCourse = courseService.getCourseById(course_id);
        courseService.deleteCourse(course_id);
        return ResponseEntity.ok(null);
    }
    @GetMapping("/bought/{user_id}")
    @PreAuthorize("hasRole('PARENT')") // can xem lai
    public ResponseEntity<List<CourseDTO>> getCoursesBoughtByParent(@PathVariable("user_id") int user_id) {
        List<CourseDTO> courses = courseService.getCoursesBoughtByParent(user_id);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/notbought/{user_id}")
    @PreAuthorize("hasRole('PARENT')")
    public ResponseEntity<List<CourseDTO>> getCoursesNotBoughtByParent(@PathVariable("user_id") int user_id) {
        List<CourseDTO> courses = courseService.getCoursesNotBoughtByParent(user_id);
        return ResponseEntity.ok(courses);
    }

}
