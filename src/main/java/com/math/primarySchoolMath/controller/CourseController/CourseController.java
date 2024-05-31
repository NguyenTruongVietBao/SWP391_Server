package com.math.primarySchoolMath.controller.CourseController;

import com.math.primarySchoolMath.dto.request.CourseDTO;
import com.math.primarySchoolMath.service.courseService.CourseService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private CourseService courseService;
    @GetMapping("/hello-world")
    String helloWorld(){
        return ("hello123");
    }
    //Add new Course
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse (@RequestBody CourseDTO courseDTO){
        CourseDTO savedCourse = courseService.createCourse(courseDTO);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }
}
