package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.EnrollmentDTO;
import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.request.QuizResultDTO;
import com.math.mathcha.entity.Enrollment;
import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.entity.Question.QuestionOption;
import com.math.mathcha.entity.QuizResult;
import com.math.mathcha.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class QuizResultMapper {

    public QuizResultDTO mapToQuizResultDTO(QuizResult quizResult){
        QuizResultDTO quizResultDTO = new QuizResultDTO();
        quizResultDTO.setQuizResult_id(quizResult.getQuizResult_id());
        quizResultDTO.setQuiz_name(quizResult.getQuiz_name());
        quizResultDTO.setScore(quizResult.getScore());
        quizResultDTO.setDate(quizResult.getDate());
        quizResultDTO.setEnrollment_id(quizResult.getEnrollment().getEnrollment_id());
        return quizResultDTO;
    }

    public static QuizResult mapToQuizResult(QuizResultDTO quizResultDTO){
        QuizResult quizResult = new QuizResult();
        quizResult.setQuizResult_id(quizResultDTO.getQuizResult_id());
        quizResult.setQuiz_name(quizResultDTO.getQuiz_name());
        quizResult.setScore(quizResultDTO.getScore());
        quizResult.setDate(quizResultDTO.getDate());

        return quizResult;
    }


}

