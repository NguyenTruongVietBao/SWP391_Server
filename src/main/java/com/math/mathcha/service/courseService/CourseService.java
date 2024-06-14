package com.math.mathcha.service.courseService;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.CourseDTO;

import java.util.List;

public interface CourseService {
    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO getCourseById ( Integer course_id) throws IdInvalidException;

    List<CourseDTO> getCourseAll();

    CourseDTO updateCourse (CourseDTO courseDTO, Integer course_id);

    void deleteCourse (Integer course_id) throws IdInvalidException;
}
