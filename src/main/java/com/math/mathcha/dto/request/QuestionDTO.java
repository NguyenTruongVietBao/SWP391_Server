package com.math.mathcha.dto.request;

import com.math.mathcha.entity.Quiz;
import com.math.mathcha.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private long question_id;

    private String content;

    private String title;

    private List<String> option;

    private String correctAnswer;

    private Topic topic;

    private Quiz quiz;

}
