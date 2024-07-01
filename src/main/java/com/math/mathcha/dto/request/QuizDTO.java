package com.math.mathcha.dto.request;

import com.math.mathcha.enums.QuizType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizDTO {
    private Long quizId;
    private int numOfQuestions;
    private int timeLimit;
    private QuizType quizType;
    private Integer courseId;
    private Integer chapterId;
    private Integer topicId;
    private List<QuestionDTO> questions;
}
