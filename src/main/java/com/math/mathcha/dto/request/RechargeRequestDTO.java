package com.math.mathcha.dto.request;


import lombok.Data;

@Data
public class RechargeRequestDTO {
    String amount;
    int studentId;
    int courseId;
}
