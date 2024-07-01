package com.math.mathcha.service.questionService;

import com.math.mathcha.dto.request.QuestionDTO;

import java.util.List;

public interface QuestionService {
    public QuestionDTO createQuestion(QuestionDTO questionDTO, Integer topic_id);

    QuestionDTO getQuestionById(Integer question_id);

    List<QuestionDTO> getQuestionsByTopicId(int question_id);

    QuestionDTO updateQuestion(QuestionDTO questionDTO, Integer question_id);

    void deleteQuestion(Integer question_id);

    void saveQuestionsFromExcel(List<QuestionDTO> questions, Integer topicId);

}
