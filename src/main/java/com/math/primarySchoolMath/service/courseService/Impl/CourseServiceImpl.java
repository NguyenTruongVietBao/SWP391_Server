//package com.math.primarySchoolMath.service.courseService.Impl;
//
//import com.math.primarySchoolMath.dto.request.CourseDTO;
//import com.math.primarySchoolMath.entity.Course;
//import com.math.primarySchoolMath.mapper.CourseMapper;
//import com.math.primarySchoolMath.repository.CourseRepository.CourseRepository;
//import com.math.primarySchoolMath.service.courseService.CourseService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class CourseServiceImpl implements CourseService {
//    private CourseRepository courseRepository;
//
//    @Override
//    public CourseDTO createCourse(CourseDTO courseDTO) {
//        Course course = CourseMapper.mapToCourse(courseDTO);
//        Course savedCourse= courseRepository.save(course);
//        return CourseMapper.mapToCourseDTO(savedCourse);
//    }
//
//    @Override
//    public CourseDTO getCourseById( Integer course_id) {
//        Course course = courseRepository.findById(course_id)
//                .orElseThrow(() -> new RuntimeException("Course "+course_id+" not found"));
//        return CourseMapper.mapToCourseDTO(course);
//    }
//
//    @Override
//    public List<CourseDTO> getCourseAll() {
//        List<Course> courses = courseRepository.findAll();
//        return courses.stream().map(
//                (course) -> CourseMapper.mapToCourseDTO(course)).collect(Collectors.toList()
//        );
//    }
//
//    @Override
//    public CourseDTO updateCourse(CourseDTO updatedCourse, Integer course_id) {
//        Course course = courseRepository.findById(course_id)
//                .orElseThrow(()-> new RuntimeException("Course "+course_id+" not found"));
//        course.setTitle(updatedCourse.getTitle());
//        course.setDescription(updatedCourse.getDescription());
//        course.setImage(updatedCourse.getImage());
//        course.setOriginal_price(updatedCourse.getOriginal_price());
//        course.setDiscount_price(updatedCourse.getDiscount_price());
//        Course updateCourseObj = courseRepository.save(course);
//        return CourseMapper.mapToCourseDTO(updateCourseObj);
//    }
//
//    @Override
//    public void deleteCourse(Integer course_id) {
//        Course course = courseRepository.findById(course_id)
//                .orElseThrow(() -> new RuntimeException("Not exits"+course_id));
//        courseRepository.deleteById(course_id);
//    }
//
//
//}
