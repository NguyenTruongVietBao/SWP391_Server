package com.math.mathcha.service.questionService;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.entity.Question.Question;

import java.util.List;

public interface QuestionService {
    QuestionDTO createQuestion(QuestionDTO questionDTO, Integer question_id);

    QuestionDTO getQuestionById(Integer question_id);

    List<QuestionDTO> getQuestionsByTopicId(int question_id);

    public QuestionDTO updateQuestion(Question updatedQuestion, Integer questionId);

    void deleteQuestion(Integer question_id);

    void saveQuestionsFromExcel(List<QuestionDTO> questions, Integer topicId);

    List<QuestionDTO> getQuestionsByChapterId(int chapterId);

}
