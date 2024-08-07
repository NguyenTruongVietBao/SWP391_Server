package com.math.mathcha.dto.request;

import com.math.mathcha.entity.Course;
import com.math.mathcha.entity.Student;
import com.math.mathcha.entity.User;
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
    private String orderId;
    private Student student;
    private Course course;
    private User user;

}
