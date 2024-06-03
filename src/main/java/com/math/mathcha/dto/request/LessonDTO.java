package com.math.mathcha.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonDTO {

    private int lesson_id;

    private String title;

    private int number;

    private String document;

    private String video_url;

    private int topic_id;
}

