package com.math.mathcha.service.courseService.Impl;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.dto.request.CourseDTO;
import com.math.mathcha.dto.request.StudentDTO;
import com.math.mathcha.entity.Category;
import com.math.mathcha.entity.Course;
import com.math.mathcha.entity.Student;
import com.math.mathcha.entity.User;
import com.math.mathcha.mapper.CourseMapper;
import com.math.mathcha.mapper.StudentMapper;
import com.math.mathcha.mapper.TopicMapper;
import com.math.mathcha.repository.CategoryRepository;
import com.math.mathcha.repository.CourseRepository.CourseRepository;
import com.math.mathcha.repository.StudentRepository.StudentRepository;
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
    private StudentRepository studentRepository;
    private CategoryRepository categoryRepository;

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO, Integer user_id) throws IdInvalidException {
        Course course = CourseMapper.mapToCourse(courseDTO);
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new IdInvalidException("User: "+user_id+" not found"));
        Category category = categoryRepository.findById(courseDTO.getCategory_id())
                .orElseThrow(() -> new IdInvalidException("Category ID " + courseDTO.getCategory_id() + " not found"));
        course.setUser(user);
        course.setCategory(category);
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
    public List<CourseDTO> getCourseByStudentId(int student_id) throws IdInvalidException {
        List<Course> courses = courseRepository.findCoursesByStudentId(student_id);
        Optional<Student> student = studentRepository.findById(student_id);
        return courses.stream()
                .map(CourseMapper::mapToCourseDTO)
                .collect(Collectors.toList());
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
        course.setStatus(updatedCourse.getStatus());
        course.setIs_deleted(updatedCourse.getIs_deleted());
        Category category = categoryRepository.findById(updatedCourse.getCategory_id())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        course.setCategory(category);
        Course updateCourseObj = courseRepository.save(course);
        return CourseMapper.mapToCourseDTO(updateCourseObj);
    }

    @Override
    public void deleteCourse(Integer course_id) throws IdInvalidException{
        Course course = courseRepository.findById(course_id)
                .orElseThrow(() -> new IdInvalidException("Course với id = " + course_id + " không tồn tại"));
        courseRepository.deleteById(course_id);
    }
    @Override
    public List<CourseDTO> getCoursesBoughtByParent(int user_id) {
        List<Course> courses = courseRepository.findCoursesBoughtByParent(user_id);
        return courses.stream()
                .map(CourseMapper::mapToCourseDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<CourseDTO> getCoursesNotBoughtByParent(int user_id) {
        List<Course> courses = courseRepository.findCoursesNotBoughtByParent(user_id);
        return courses.stream()
                .map(CourseMapper::mapToCourseDTO)
                .collect(Collectors.toList());
    }



}
