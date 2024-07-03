package com.math.mathcha.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.math.mathcha.entity.Enrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultDTO {
    private int quizResult_id;
    private int score;
    private LocalDateTime date;
    private int enrollment_id;
}
