//package com.math.mathcha.controller.CourseController;
//
//import com.math.mathcha.dto.request.CourseDTO;
//import com.math.mathcha.service.courseService.CourseService;
//
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin("*")
//@RestController
//@AllArgsConstructor
//@RequestMapping("/course")
//public class CourseController {
//
//    private CourseService courseService;
//
//    @PostMapping
//    public ResponseEntity<CourseDTO> createCourse (@RequestBody CourseDTO courseDTO){
//        CourseDTO savedCourse = courseService.createCourse(courseDTO);
//        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
//    }
//
//    @GetMapping("{course_id}")
//    public ResponseEntity<CourseDTO> getCourseById (@PathVariable("course_id") Integer course_id){
//        CourseDTO courseDTO = courseService.getCourseById(course_id);
//        return ResponseEntity.ok(courseDTO);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CourseDTO>> getCourseAll(){
//        List<CourseDTO> courses = courseService.getCourseAll();
//        return ResponseEntity.ok(courses);
//    }
//
//    @PutMapping("/update/{course_id}")
//    public ResponseEntity<CourseDTO> updateCourse (@RequestBody CourseDTO updatedCourse, @PathVariable("course_id") Integer courseId){
//        CourseDTO courseDTO = courseService.updateCourse(updatedCourse, courseId );
//        return ResponseEntity.ok(courseDTO);
//    }
//
//    @DeleteMapping("{course_id}")
//    public ResponseEntity<String> deleteCourse(@PathVariable("course_id") Integer course_id){
//        courseService.deleteCourse(course_id);
//        return ResponseEntity.ok("Delete successfully !");
//    }
//}
