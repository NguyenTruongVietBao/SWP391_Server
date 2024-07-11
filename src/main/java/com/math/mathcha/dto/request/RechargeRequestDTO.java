package com.math.mathcha.dto.request;


import com.math.mathcha.entity.Course;
import com.math.mathcha.entity.Enrollment;
import com.math.mathcha.entity.Student;
import lombok.Data;

@Data
public class  RechargeRequestDTO {
    String amount;
    int student_id;
    int course_id;
    int enrollment_id;
    int user_id;

}
