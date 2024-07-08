package com.math.mathcha.dto.response;

import com.math.mathcha.entity.Course;
import com.math.mathcha.entity.Enrollment;
import com.math.mathcha.entity.Student;
import com.math.mathcha.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class ResChartDTO {
    List<String> labels;
    List<Double> revenue;
    private double totalRevenue;
    private List<User> users;
    private List<Course> courses;

    private double dailyRevenue;
    private double monthlyRevenue;
    private double yearlyRevenue;

}
