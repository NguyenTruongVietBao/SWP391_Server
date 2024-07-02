package com.math.mathcha.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentTopicDTO {
    private int enrollmentTopic_id;
    private Boolean is_complete = true;
    private int enrollment_id;
    private int topic_id;
}
