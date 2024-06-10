package com.math.mathcha.dto.request;

import com.math.mathcha.entity.Course;
import com.math.mathcha.entity.Quiz;
import com.math.mathcha.entity.Topic;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDTO {
    private int chapter_id;
    private String title;
    private int number;
//    private Course course;
//    private List<Topic> topics;
//    private List<Quiz> quizs;
}
