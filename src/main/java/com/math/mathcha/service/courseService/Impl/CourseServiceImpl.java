package com.math.mathcha.service.courseService.Impl;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.CourseDTO;
import com.math.mathcha.entity.Course;
import com.math.mathcha.entity.User;
import com.math.mathcha.mapper.CourseMapper;
import com.math.mathcha.mapper.TopicMapper;
import com.math.mathcha.repository.CourseRepository.CourseRepository;
import com.math.mathcha.repository.UserRepository.UserRepository;
import com.math.mathcha.service.courseService.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;
    private UserRepository userRepository;
    @Override
    public CourseDTO createCourse(CourseDTO courseDTO, Integer user_id) throws IdInvalidException {
        Course course = CourseMapper.mapToCourse(courseDTO);
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new IdInvalidException("User: "+user_id+" not found"));
        course.setUser(user);
        Course savedCourse = courseRepository.save(course);
        return CourseMapper.mapToCourseDTO(savedCourse);
    }

    @Override
    public CourseDTO getCourseById( Integer course_id) throws IdInvalidException {
        Optional<Course> course = courseRepository.findById(course_id);
        if (course.isPresent()) {
            return CourseMapper.mapToCourseDTO(course.get());
        }else {
            throw new IdInvalidException("Course với id = " + course_id + " không tồn tại");
        }
    }

    @Override
    public List<CourseDTO> getCourseAll() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(
                (course) -> CourseMapper.mapToCourseDTO(course)).collect(Collectors.toList()
        );
    }

    @Override
    public CourseDTO updateCourse(CourseDTO updatedCourse, Integer course_id) {
        Course course = courseRepository.findById(course_id)
                .orElseThrow(()-> new RuntimeException("Course "+course_id+" not found"));
        course.setTitle(updatedCourse.getTitle());
        course.setDescription(updatedCourse.getDescription());
        course.setImage(updatedCourse.getImage());
        course.setOriginal_price(updatedCourse.getOriginal_price());
        course.setDiscount_price(updatedCourse.getDiscount_price());
        Course updateCourseObj = courseRepository.save(course);
        return CourseMapper.mapToCourseDTO(updateCourseObj);
    }

    @Override
    public void deleteCourse(Integer course_id) throws IdInvalidException{
        Course course = courseRepository.findById(course_id)
                .orElseThrow(() -> new IdInvalidException("Course với id = " + course_id + " không tồn tại"));
        courseRepository.deleteById(course_id);
    }


}
