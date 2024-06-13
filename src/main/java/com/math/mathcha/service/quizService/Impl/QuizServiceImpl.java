package com.math.mathcha.service.quizService.Impl;

import com.math.mathcha.dto.request.LessonDTO;
import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.entity.Lesson;
import com.math.mathcha.entity.Quiz;
import com.math.mathcha.entity.Topic;
import com.math.mathcha.mapper.LessonMapper;
import com.math.mathcha.mapper.QuizMapper;
import com.math.mathcha.repository.QuizRepository.QuizRepository;
import com.math.mathcha.repository.TopicRepository.TopicRepository;
import com.math.mathcha.service.quizService.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {
    private QuizRepository quizRepository;
    private final TopicRepository topicRepository;

    @Override
    public QuizDTO createQuiz(QuizDTO quizDTO, Integer topic_id) {
        Quiz quiz = QuizMapper.mapToQuiz(quizDTO);
        Topic topic = topicRepository.findById(topic_id)
                .orElseThrow(() -> new RuntimeException("Topic: "+topic_id+" not found"));
        quiz.setTopic(topic);
        Quiz savedQuiz = quizRepository.save(quiz);
        return QuizMapper.mapToQuizDTO(savedQuiz);
    }

    @Override
    public QuizDTO getQuizById(Integer quiz_id) {
        Optional<Quiz> quiz = quizRepository.findById(quiz_id);
        if(quiz.isPresent()) {
            return QuizMapper.mapToQuizDTO(quiz.get());
        }
        return null;
    }

    @Override
    public List<QuizDTO> getQuizByTopicId(int topic_id) {
        List<Quiz> quizs = quizRepository.findQuizByTopicId(topic_id);
        return quizs.stream().map(
                (quiz) -> QuizMapper.mapToQuizDTO(quiz)).collect(Collectors.toList()

        );
    }

    @Override
    public QuizDTO updateQuiz(QuizDTO updateQuiz, Integer quiz_id) {
        Quiz quiz = quizRepository.findById(quiz_id)
                .orElseThrow(() -> new RuntimeException("Quiz: "+quiz_id+" not found"));
        quiz.setTitle(updateQuiz.getTitle());
        quiz.setContent(updateQuiz.getContent());
        quiz.setCorrectAnswer(updateQuiz.getCorrectAnswer());
        Quiz uptateQuizsObj = quizRepository.save(quiz);
        return QuizMapper.mapToQuizDTO(uptateQuizsObj);
    }

    @Override
    public void deleteQuiz(Integer quiz_id) {
        Quiz quiz = quizRepository.findById(quiz_id)
                .orElseThrow(() -> new RuntimeException("Not exits "+quiz_id));
        quizRepository.deleteById(quiz_id);
    }


}
