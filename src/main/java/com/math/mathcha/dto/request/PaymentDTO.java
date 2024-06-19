package com.math.mathcha.dto.request;

import com.math.mathcha.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private int payment_id;
    private Date payment_date;
    private String payment_method;
    private String total_money;
    private Student student_id;

}
