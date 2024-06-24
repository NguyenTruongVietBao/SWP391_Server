package com.math.mathcha.dto.request;

import com.math.mathcha.entity.Course;
import com.math.mathcha.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private int payment_id;
    private String payment_date;
    private String payment_method;
    private double total_money;
    private Student student;
    private Course course;
}
