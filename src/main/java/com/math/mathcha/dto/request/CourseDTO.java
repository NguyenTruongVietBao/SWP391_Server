package com.math.mathcha.dto.request;

import com.math.mathcha.entity.Chapter;
import com.math.mathcha.entity.Enrollment;
import com.math.mathcha.entity.Quiz;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private int course_id;
    private String title;
    private String description;
    private String image;
    private String original_price;
    private String discount_price;

//    private List<Chapter> chapters;
//    private List<Quiz> quizs;
//    private List<Enrollment> enrollments;
}
