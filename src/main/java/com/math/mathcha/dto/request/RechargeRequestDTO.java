package com.math.mathcha.dto.request;


import com.math.mathcha.entity.Course;
import com.math.mathcha.entity.Student;
import lombok.Data;

@Data
public class RechargeRequestDTO {
    String amount;
    Long studentId;
    Long courseId;
}
