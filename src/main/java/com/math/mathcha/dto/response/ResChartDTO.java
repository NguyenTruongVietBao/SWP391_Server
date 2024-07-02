package com.math.mathcha.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ResChartDTO {
    List<String> labels;
    List<Double> revenue;
    private double totalRevenue;
    private double dailyRevenue;
    private double monthlyRevenue;
    private double yearlyRevenue;
}
