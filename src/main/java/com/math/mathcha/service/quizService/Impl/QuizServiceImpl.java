package com.math.mathcha.service.quizService.Impl;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.dto.request.QuizResultDTO;
import com.math.mathcha.dto.response.ResQuizResultDTO;
import com.math.mathcha.entity.*;
import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.mapper.QuestionMapper;
import com.math.mathcha.mapper.QuizResultMapper;
import com.math.mathcha.repository.ChapterRepository.ChapterRepository;
import com.math.mathcha.repository.CourseRepository.CourseRepository;
import com.math.mathcha.repository.EnrollmentRepository;
import com.math.mathcha.repository.QuestionRepository.QuestionRepository;
import com.math.mathcha.repository.QuizResultRepository.QuizResultRepository;
import com.math.mathcha.repository.TopicRepository.TopicRepository;
import com.math.mathcha.service.questionService.QuestionService;
import com.math.mathcha.service.quizService.QuizService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizResultRepository quizResultRepository;
    private final QuestionRepository questionRepository;
    private final ChapterRepository chapterRepository;
    private final TopicRepository topicRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final QuestionService questionService;
    @Autowired
    private CourseRepository courseRepository;


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
    public Quiz generateQuizForChapter(int chapterId, int questionPerTopic, int timeLimit) {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new EntityNotFoundException("Chapter not found"));
        List<QuestionDTO> quizQuestions = new ArrayList<>();
        for (Topic topic : chapter.getTopics()) {
            List<Question> questions = questionRepository.findByTopic(topic);
            Collections.shuffle(questions);
            List<Question> selectedQuestions = questions.subList(0, Math.min(questionPerTopic, questions.size()));
            quizQuestions.addAll(selectedQuestions.stream()
                    .map(QuestionMapper::mapToQuestionDTO)
                    .collect(Collectors.toList()));
        }
        Quiz quiz = new Quiz();
        quiz.setQuestions(quizQuestions);
        quiz.setNumberOfQuestions(questionPerTopic);
        quiz.setTimeLimit(timeLimit);
        return quiz;
    }

    @Override
    public Quiz generateQuizForCourse(int courseId, int questionPerChapter, int timeLimit) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khóa học"));

        List<Question> quizQuestions = new ArrayList<>();

        for (Chapter chapter : course.getChapters()) {
            List<QuestionDTO> chapterQuestions = questionService.getQuestionsByChapterId(chapter.getChapter_id());
            Collections.shuffle(chapterQuestions);

            List<QuestionDTO> selectedQuestions = chapterQuestions.subList(0, Math.min(questionPerChapter, chapterQuestions.size()));

            quizQuestions.addAll(
                    selectedQuestions.stream()
                            .map(QuestionMapper::mapToQuestion)
                            .collect(Collectors.toList())
            );
        }

        Quiz quiz = new Quiz();
        quiz.setQuestions(
                quizQuestions.stream()
                        .map(QuestionMapper::mapToQuestionDTO)
                        .collect(Collectors.toList())
        );
        quiz.setNumberOfQuestions(quizQuestions.size()); // Đặt số lượng câu hỏi thực tế trong bài kiểm tra
        quiz.setTimeLimit(timeLimit);

        return quiz;
    }



    @Override
    public Quiz generateQuizForTopic(int topicId, int numberOfQuestions, int timeLimit) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));
        List<Question> questions = questionRepository.findByTopic(topic);
        Collections.shuffle(questions);
        List<Question> selectedQuestions = questions.subList(0, Math.min(numberOfQuestions, questions.size()));
        List<QuestionDTO> quizQuestions = selectedQuestions.stream()
                .map(QuestionMapper::mapToQuestionDTO)
                .collect(Collectors.toList());

        Quiz quiz = new Quiz();
        quiz.setQuestions(quizQuestions);
        quiz.setNumberOfQuestions(numberOfQuestions);
        quiz.setTimeLimit(timeLimit);
        return quiz;
    }



    @Override
    public ResQuizResultDTO evaluateQuiz( int enrollment_id, QuizDTO quizDTO) {
        List<QuestionDTO> questions = quizDTO.getQuestions();
        List<String> userAnswers = quizDTO.getUserAnswer();
        List<Boolean> results = new ArrayList<>();
        int correctCount = 0;

        for (int i = 0; i < questions.size(); i++) {
            QuestionDTO question = questions.get(i);
            String userAnswer = userAnswers.get(i);

            boolean isCorrect = question.getCorrectAnswer().equals(userAnswer);
            results.add(isCorrect);

            if (isCorrect) {
                correctCount++;
            }
        }

        QuizResultDTO quizResultDTO = new QuizResultDTO();

        quizResultDTO.setEnrollment_id(enrollment_id);
        quizResultDTO.setScore(correctCount);
        quizResultDTO.setDate(LocalDateTime.now());
        QuizResult quizResult = QuizResultMapper.mapToQuizResult(quizResultDTO);

        Enrollment enrollment = enrollmentRepository.findById(quizResultDTO.getEnrollment_id()).orElseThrow(() -> new EntityNotFoundException("Enrollment not found"));
        quizResult.setEnrollment(enrollment);
        quizResultRepository.save(quizResult);


        ResQuizResultDTO resQuizResultDTO = new ResQuizResultDTO(results, correctCount, questions.size());

        return resQuizResultDTO;
    }


}
