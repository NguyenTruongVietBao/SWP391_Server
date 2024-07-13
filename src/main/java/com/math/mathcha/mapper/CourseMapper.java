package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.CourseDTO;
import com.math.mathcha.entity.Course;

public class CourseMapper {
    public static CourseDTO mapToCourseDTO(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourse_id(course.getCourse_id());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setImage(course.getImage());
        courseDTO.setOriginal_price(course.getOriginal_price());
        courseDTO.setDiscount_price(course.getDiscount_price());
        courseDTO.setIs_deleted(course.getIs_deleted());
        courseDTO.setStatus(course.getStatus());
        courseDTO.setCategory_id(course.getCategory().getCategory_id());

        return courseDTO;
    }

    public static Course mapToCourse(CourseDTO courseDTO){
        Course course = new Course();
        course.setCourse_id(courseDTO.getCourse_id());
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setImage(courseDTO.getImage());
        course.setOriginal_price(courseDTO.getOriginal_price());
        course.setDiscount_price(courseDTO.getDiscount_price());
        course.setIs_deleted(courseDTO.getIs_deleted());
        course.setStatus(courseDTO.getStatus());

        return course;
    }
}
