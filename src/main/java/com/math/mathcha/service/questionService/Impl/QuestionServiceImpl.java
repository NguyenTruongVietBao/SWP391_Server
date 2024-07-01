package com.math.mathcha.service.questionService.Impl;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.entity.Question.QuestionOption;
import com.math.mathcha.entity.Quiz;
import com.math.mathcha.entity.Topic;
import com.math.mathcha.mapper.QuestionMapper;
import com.math.mathcha.repository.QuestionRepository.QuestionRepository;
import com.math.mathcha.repository.QuizRepository.QuizRepository;
import com.math.mathcha.repository.TopicRepository.TopicRepository;
import com.math.mathcha.service.questionService.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private QuestionRepository questionRepository;
    private final TopicRepository topicRepository;
    private final QuizRepository quizRepository;

    public QuestionDTO createQuestion(QuestionDTO questionDTO, Integer topicId) {
        Question question = QuestionMapper.mapToQuestion(questionDTO);
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException("Topic with ID " + topicId + " not found"));
//        Quiz quiz = quizRepository.findById(quizId)
//                .orElseThrow(() -> new EntityNotFoundException("Quiz with ID " + quizId + " not found"));
        question.setTopic(topic);
//        question.setQuiz(quiz);

        List<QuestionOption> options = questionDTO.getOption().stream()
                .map(optionText -> {
                    QuestionOption option = new QuestionOption();
                    option.setOptionText(optionText);
                    option.setQuestion(question);
                    return option;
                }).collect(Collectors.toList());

        question.setOptions(options);
        Question savedQuestion = questionRepository.save(question);
        return QuestionMapper.mapToQuestionDTO(savedQuestion);
    }






    @Override
    public QuestionDTO getQuestionById(Integer question_id) {
        Optional<Question> question = questionRepository.findById(question_id);
        if(question.isPresent()) {
            return QuestionMapper.mapToQuestionDTO(question.get());
        }
        return null;
    }

    @Override
    public List<QuestionDTO> getQuestionsByTopicId(int topic_id) {
        List<Question> questions = questionRepository.findQuestionByTopicId(topic_id);
        return questions.stream().map(
                (question) -> QuestionMapper.mapToQuestionDTO(question)).collect(Collectors.toList()

        );
    }

    @Override
    public QuestionDTO updateQuestion(QuestionDTO updateQuestion, Integer question_id) {
        Question question = questionRepository.findById(question_id)
                .orElseThrow(() -> new RuntimeException("Question: "+question_id+" not found"));
        question.setTitle(updateQuestion.getTitle());
        question.setContent(updateQuestion.getContent());
        question.setCorrectAnswer(updateQuestion.getCorrectAnswer());
        Question uptateQuestionsObj = questionRepository.save(question);
        return QuestionMapper.mapToQuestionDTO(uptateQuestionsObj);
    }

    @Override
    public void deleteQuestion(Integer question_id) {
        Question question = questionRepository.findById(question_id)
                .orElseThrow(() -> new RuntimeException("Not exits "+question_id));
        questionRepository.deleteById(question_id);
    }

    @Override
    public void saveQuestionsFromExcel(List<QuestionDTO> questions, Integer topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic: " + topicId + " not found"));
        for (QuestionDTO questionDTO : questions) {
            Question question = QuestionMapper.mapToQuestion(questionDTO);
            question.setTopic(topic);
            questionRepository.save(question);
        }
    }



}
