package com.math.primarySchoolMath.mapper;

import com.math.primarySchoolMath.dto.request.CourseDTO;
import com.math.primarySchoolMath.entity.Course;

public class CourseMapper {
    public static CourseDTO mapToCourseDTO(Course course){
        return new CourseDTO(
                course.getCourse_id(),
                course.getTitle(),
                course.getDescription(),
                course.getImage(),
                course.getOriginal_price(),
                course.getDiscount_price()
        );
    }

    public static Course mapToCourse(CourseDTO courseDTO){
        return new Course(
            courseDTO.getCourse_id(),
            courseDTO.getTitle(),
            courseDTO.getDescription(),
            courseDTO.getImage(),
            courseDTO.getOriginal_price(),
            courseDTO.getDiscount_price()
        );
    }
}
