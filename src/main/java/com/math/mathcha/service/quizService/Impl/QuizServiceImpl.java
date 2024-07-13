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
    public Quiz generateQuizForChapter(int chapterId , int numberOfQuestions, int timeLimit) {
        int questionPerTopic = 10;
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new EntityNotFoundException("Chapter not found"));
        List<QuestionDTO> quizQuestions = new ArrayList<>();
        int questionsAdded = 0;

        for (Topic topic : chapter.getTopics()) {
            if (questionsAdded >= numberOfQuestions) {
                break;
            }

            List<Question> questions = questionRepository.findByTopic(topic);
            Collections.shuffle(questions);
            int remainingQuestions = numberOfQuestions - questionsAdded;
            List<Question> selectedQuestions = questions.subList(0, Math.min(Math.min(questionPerTopic, questions.size()), remainingQuestions));

            quizQuestions.addAll(selectedQuestions.stream()
                    .map(QuestionMapper::mapToQuestionDTO)
                    .collect(Collectors.toList()));

            questionsAdded += selectedQuestions.size();
        }

        Quiz quiz = new Quiz();
        quiz.setQuestions(quizQuestions);
        quiz.setNumberOfQuestions(quizQuestions.size());
        quiz.setTimeLimit(timeLimit);
        return quiz;
    }



    @Override
    public Quiz generateQuizForCourse(int courseId, int numberOfQuestions, int timeLimit) {
        int questionPerChapter = 10;
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khóa học"));
        List<Question> quizQuestions = new ArrayList<>();
        int questionsAdded = 0;

        for (Chapter chapter : course.getChapters()) {
            if (questionsAdded >= numberOfQuestions) {
                break;
            }

            List<QuestionDTO> chapterQuestions = questionService.getQuestionsByChapterId(chapter.getChapter_id());
            Collections.shuffle(chapterQuestions);
            int remainingQuestions = numberOfQuestions - questionsAdded;
            List<QuestionDTO> selectedQuestions = chapterQuestions.subList(0, Math.min(Math.min(questionPerChapter, chapterQuestions.size()), remainingQuestions));

            quizQuestions.addAll(
                    selectedQuestions.stream()
                            .map(QuestionMapper::mapToQuestion)
                            .collect(Collectors.toList())
            );

            questionsAdded += selectedQuestions.size();
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

    public QuizResultDTO saveQuiz(int enrollment_id, int score, String quiz_name) {
        // Create a new QuizResultDTO
        QuizResultMapper quizResultMapper = new QuizResultMapper();
        QuizResultDTO quizResultDTO = new QuizResultDTO();
        quizResultDTO.setQuiz_name(quiz_name);
        quizResultDTO.setEnrollment_id(enrollment_id);
        quizResultDTO.setScore(score);
        quizResultDTO.setDate(LocalDateTime.now());
        QuizResult quizResult = quizResultMapper.mapToQuizResult(quizResultDTO);
        Enrollment enrollment = enrollmentRepository.findById(enrollment_id)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found"));
        quizResult.setEnrollment(enrollment);
        quizResult = quizResultRepository.save(quizResult);
        quizResultDTO.setQuizResult_id(quizResult.getQuizResult_id());

        return quizResultDTO;
    }

    public List<QuizResultDTO> getQuizResultByEnrollmentId(int enrollment_id) {
        QuizResultMapper quizResultMapper = new QuizResultMapper();
        List<QuizResult> quizResults = quizResultRepository.findByEnrollment_id(enrollment_id);
        return quizResults.stream()
                .map(quizResultMapper::mapToQuizResultDTO)
                .collect(Collectors.toList());
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
