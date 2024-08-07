package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.entity.Question.QuestionOption;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionMapper {
    public static Question mapToQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setQuestion_id(questionDTO.getQuestion_id());
        question.setContent(questionDTO.getContent());
        question.setTitle(questionDTO.getTitle());
        question.setCorrectAnswer(questionDTO.getCorrectAnswer());
        List<QuestionOption> options = questionDTO.getOption().stream()
                .map(opt -> {
                    QuestionOption option = new QuestionOption();
                    option.setOption(opt);
                    option.setQuestion(question);
                    return option;
                }).collect(Collectors.toList());
        question.setQuestionOptions(options);
        return question;
    }

    public static QuestionDTO mapToQuestionDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestion_id(question.getQuestion_id());
        questionDTO.setContent(question.getContent());
        questionDTO.setTitle(question.getTitle());
        questionDTO.setCorrectAnswer(question.getCorrectAnswer());
        questionDTO.setOption(question.getQuestionOptions().stream()
                .map(QuestionOption::getOption)
                .collect(Collectors.toList()));
        return questionDTO;
    }
}

