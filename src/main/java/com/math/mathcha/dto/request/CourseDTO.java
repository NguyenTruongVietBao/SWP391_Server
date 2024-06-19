package com.math.mathcha.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private int course_id;
    private String title;
    private String description;
    private String image;
    private String original_price;
    private String discount_price;
    private Boolean is_public;
    private Boolean is_delete;
}
