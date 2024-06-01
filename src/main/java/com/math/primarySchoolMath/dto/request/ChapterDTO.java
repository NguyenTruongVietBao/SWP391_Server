package com.math.primarySchoolMath.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDTO {

    private int chapter_id;

    private String title;

    private int number;

    private int course_id;
}
