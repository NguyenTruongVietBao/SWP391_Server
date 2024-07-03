package com.math.mathcha.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentChapterDTO {
    private int enrollmentChapter_id;
    private Boolean is_complete = true;
    private int enrollment_id;
    private int chapter_id;
}
