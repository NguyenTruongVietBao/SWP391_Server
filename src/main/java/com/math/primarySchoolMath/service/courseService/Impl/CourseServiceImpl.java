package com.math.primarySchoolMath.service.courseService.Impl;

import com.math.primarySchoolMath.dto.request.CourseDTO;
import com.math.primarySchoolMath.entity.Course;
import com.math.primarySchoolMath.mapper.CourseMapper;
import com.math.primarySchoolMath.repository.CourseRepository.CourseRepository;
import com.math.primarySchoolMath.service.courseService.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = CourseMapper.mapToCourse(courseDTO);
        Course savedCourse= courseRepository.save(course);
        return CourseMapper.mapToCourseDTO(savedCourse);
    }
}
