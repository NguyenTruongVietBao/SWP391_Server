package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.entity.Question.QuestionOption;
import com.math.mathcha.entity.Quiz;
import com.math.mathcha.enums.QuizType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuizMapper {
    public static QuizDTO mapToQuizDTO(Quiz quiz) {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setQuizId(quiz.getQuiz_id());
        quizDTO.setNumOfQuestions(quiz.getNumOfQuestions());
        quizDTO.setTimeLimit(quiz.getTimeLimit());
        quizDTO.setQuizType(quiz.getQuizType());

        if (quiz.getCourse_id() != null) {
            quizDTO.setCourseId(quiz.getCourse_id().getCourse_id());
        }
        if (quiz.getChapter_id() != null) {
            quizDTO.setChapterId(quiz.getChapter_id().getChapter_id());
        }
        if (quiz.getTopic_id() != null) {
            quizDTO.setTopicId(quiz.getTopic_id().getTopic_id());
        }

        quizDTO.setQuestions(quiz.getQuestions().stream()
                .map(QuestionMapper::mapToQuestionDTO)
                .collect(Collectors.toList()));
        return quizDTO;
    }

    public static Quiz mapToQuiz(QuizDTO quizDTO) {
        Quiz quiz = new Quiz();
        quiz.setNumOfQuestions(quizDTO.getNumOfQuestions());
        quiz.setTimeLimit(quizDTO.getTimeLimit());
        quiz.setQuizType(quizDTO.getQuizType());

        if (quizDTO.getQuestions() != null) {
            quiz.setQuestions(quizDTO.getQuestions().stream()
                    .map(QuestionMapper::mapToQuestion)
                    .collect(Collectors.toList()));
        } else {
            quiz.setQuestions(new ArrayList<>()); // Khởi tạo danh sách rỗng nếu questions là null
        }

        return quiz;
    }
}
