package com.math.mathcha.service.quizService.Impl;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.dto.response.QuizResultDTO;
import com.math.mathcha.entity.Chapter;
import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.entity.Topic;
import com.math.mathcha.mapper.QuestionMapper;
import com.math.mathcha.repository.ChapterRepository.ChapterRepository;
import com.math.mathcha.repository.QuestionRepository.QuestionRepository;
import com.math.mathcha.service.quizService.QuizService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuestionRepository questionRepository;
    private final ChapterRepository chapterRepository;

    @Override
    public List<QuestionDTO> getQuizQuestions() {
        List<Question> questions = questionRepository.findAll();
        List<QuestionDTO> questionDTOs = new ArrayList<>();

        for (Question question : questions) {
            questionDTOs.add(QuestionMapper.mapToQuestionDTO(question));

        }
        return questionDTOs;
    }

    @Override
    public List<Question> generateQuizForChapter(int chapterId, int questionPerTopic) {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new EntityNotFoundException("Chapter not found"));
        List<Question> quizQuestions = new ArrayList<>();
        for (Topic topic : chapter.getTopics()) {
            List<Question> questions = questionRepository.findByTopic(topic);
            Collections.shuffle(questions);
            quizQuestions.addAll(questions.subList(0, Math.min(questionPerTopic, questions.size())));
        }
        return quizQuestions;
    }


    @Override
    public QuizResultDTO evaluateQuiz(QuizDTO quizDTO) {
        List<QuestionDTO> questions = quizDTO.getQuestions();
        List<String> userAnswers = quizDTO.getUserAnswer();
        List<Boolean> results = new ArrayList<>();
        int correctCount = 0;

        for(int i = 0; i < questions.size(); i++){
            QuestionDTO question = questions.get(i);
            String userAnswer = userAnswers.get(i);

            boolean isCorrect = question.getCorrectAnswer().equals(userAnswer);
            results.add(isCorrect);

            if(isCorrect){
                correctCount++;
            }
        }
        return new QuizResultDTO(results, correctCount, questions.size());
    }
}
