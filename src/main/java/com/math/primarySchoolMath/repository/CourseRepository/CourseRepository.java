package com.math.primarySchoolMath.repository.CourseRepository;

import com.math.primarySchoolMath.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
