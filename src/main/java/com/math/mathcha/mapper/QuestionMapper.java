package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.entity.Question;

public class QuestionMapper {
    public static QuestionDTO mapToQuestionDTO(Question question){
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestion_id(question.getQuestion_id());
        questionDTO.setContent(question.getContent());
        questionDTO.setTitle(question.getTitle());
        questionDTO.setOption(question.getOptions());
        questionDTO.setCorrectAnswer(question.getCorrectAnswer());
        return questionDTO;
    }

    public static Question mapToQuestion(QuestionDTO questionDTO){
        Question question = new Question();
        question.setQuestion_id(questionDTO.getQuestion_id());
        question.setTitle(questionDTO.getTitle());
        question.setContent(questionDTO.getContent());
        question.setOptions(questionDTO.getOption());
        question.setCorrectAnswer(questionDTO.getCorrectAnswer());
        return question;
    }
}
