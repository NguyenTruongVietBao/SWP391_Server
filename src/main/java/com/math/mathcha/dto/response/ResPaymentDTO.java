package com.math.mathcha.dto.response;

import com.math.mathcha.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResPaymentDTO {
    private int payment_id;
    private String payment_date;
    private String payment_method;
    private double total_money;
    private String orderId;
    private User user;
}
