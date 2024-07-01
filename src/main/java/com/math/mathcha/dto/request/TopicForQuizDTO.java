package com.math.mathcha.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicForQuizDTO {
    private int topicId;
    private String title;
    private int number;
    private Boolean isProgressLimited;

}

