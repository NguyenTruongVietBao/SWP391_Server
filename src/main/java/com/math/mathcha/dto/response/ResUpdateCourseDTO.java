package com.math.mathcha.dto.response;

import com.math.mathcha.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResUpdateCourseDTO {
    private int course_id;
    private String title;
    private String description;
    private String image;
    private String original_price;
    private String discount_price;
}
