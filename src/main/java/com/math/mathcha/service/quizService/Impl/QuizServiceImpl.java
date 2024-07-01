package com.math.mathcha.service.quizService.Impl;


import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.dto.response.QuizResultDTO;
import com.math.mathcha.entity.*;
import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.enums.QuizType;
import com.math.mathcha.mapper.QuestionMapper;
import com.math.mathcha.mapper.QuizMapper;
import com.math.mathcha.repository.ChapterRepository.ChapterRepository;
import com.math.mathcha.repository.CourseRepository.CourseRepository;
import com.math.mathcha.repository.QuestionRepository.QuestionRepository;
import com.math.mathcha.repository.QuizRepository.QuizRepository;
import com.math.mathcha.repository.QuizRepository.QuizResultRepository;
import com.math.mathcha.repository.TopicRepository.TopicRepository;
import com.math.mathcha.service.quizService.QuizService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizResultRepository quizResultRepository;
    private final QuestionRepository questionRepository;
    private final TopicRepository topicRepository;
    private final ChapterRepository chapterRepository;
    private final CourseRepository courseRepository;

    @Override
    public Quiz createQuiz(QuizDTO quizDTO) {
        Quiz quiz = QuizMapper.mapToQuiz(quizDTO);

        if (quizDTO.getQuizType() == QuizType.QUIZ_COURSE && quizDTO.getCourseId() != null) {
            Course course = courseRepository.findById(quizDTO.getCourseId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khóa học"));
            quiz.setCourse(course);
        } else if (quizDTO.getQuizType() == QuizType.QUIZ_CHAPTER && quizDTO.getChapterId() != null) {
            Chapter chapter = chapterRepository.findById(quizDTO.getChapterId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy chương"));
            quiz.setChapter(chapter);
        } else if (quizDTO.getQuizType() == QuizType.QUIZ_TOPIC && quizDTO.getTopicId() != null) {
            Topic topic = topicRepository.findById(quizDTO.getTopicId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy chủ đề"));
            quiz.setTopic(topic);
        } else {
            throw new IllegalArgumentException("Loại bài kiểm tra hoặc ID không hợp lệ");
        }

        return quizRepository.save(quiz);
    }

    // Hợp nhất phương thức generateQuizForTopic
    @Override
    public QuizDTO generateQuizForTopic(int topicId, int numOfQuestions, int timeLimit) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy chủ đề"));
        List<Question> questions = questionRepository.findByTopic(topic);
        Collections.shuffle(questions);
        List<Question> selectedQuestions = questions.subList(0, Math.min(numOfQuestions, questions.size()));

        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setQuestions(selectedQuestions.stream()
                .map(QuestionMapper::mapToQuestionDTO)
                .collect(Collectors.toList()));
        quizDTO.setNumOfQuestions(numOfQuestions);
        quizDTO.setTimeLimit(timeLimit);
        quizDTO.setQuizType(QuizType.QUIZ_TOPIC);
        quizDTO.setTopicId(topicId);
        Quiz quiz = QuizMapper.mapToQuiz(quizDTO);
        quizRepository.save(quiz);
        return quizDTO;
    }

    @Override
    public QuizResult saveQuizResult(Long quizId, QuizResultDTO quizResultDTO) {
        return null;
    }

    @Override
    public Quiz generateQuizForChapter(int chapterId, int numOfQuestions, int timeLimit) {
        return null;
    }

    @Override
    public Quiz generateQuizForCourse(int courseId, int numOfQuestions, int timeLimit) {
        return null;
    }

//    @Override
//    public Quiz generateQuizForChapter(int chapterId, int numOfQuestions, int timeLimit) {
//        Chapter chapter = chapterRepository.findById(chapterId)
//                .orElseThrow(() -> new EntityNotFoundException("Chapter not found"));
//        List<Question> questions = questionRepository.findByChapter(chapter);
//        Collections.shuffle(questions);
//        List<Question> selectedQuestions = questions.subList(0, Math.min(numOfQuestions, questions.size()));
//
//        Quiz quiz = new Quiz();
//        quiz.setQuestions(selectedQuestions);
//        quiz.setNumOfQuestions(numOfQuestions);
//        quiz.setTimeLimit(timeLimit);
//        quiz.setQuizType(QuizType.QUIZ_CHAPTER);
//        quiz.setChapterId(chapterId);
//        return quiz;
//    }
//
//    @Override
//    public Quiz generateQuizForCourse(int courseId, int numOfQuestions, int timeLimit) {
//        Course course = courseRepository.findById(courseId)
//                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
//        List<Question> questions = questionRepository.findByCourse(course);
//        Collections.shuffle(questions);
//        List<Question> selectedQuestions = questions.subList(0, Math.min(numOfQuestions, questions.size()));
//
//        Quiz quiz = new Quiz();
//        quiz.setQuestions(selectedQuestions);
//        quiz.setNumOfQuestions(numOfQuestions);
//        quiz.setTimeLimit(timeLimit);
//        quiz.setQuizType(QuizType.QUIZ_COURSE);
//        quiz.setCourseId(courseId);
//        return quiz;
//    }
//
//    @Override
//    public QuizResult saveQuizResult(Long quizId, QuizResultDTO quizResultDTO) {
//        Quiz quiz = quizRepository.findById(quizId)
//                .orElseThrow(() -> new RuntimeException("Quiz not found"));
//        QuizResult quizResult = new QuizResult();
//        quizResult.setQuiz(quiz);
//        quizResult.setScore(quizResultDTO.getScore());
//        quizResult.setDate(quizResultDTO.getDate() != null ? quizResultDTO.getDate() : LocalDateTime.now());
//        quizResult.setEnrollmentId(quizResultDTO.getEnrollmentId());
//        return quizResultRepository.save(quizResult);
//    }
}
















//import com.math.mathcha.dto.request.QuestionDTO;
//import com.math.mathcha.dto.request.QuizDTO;
//import com.math.mathcha.dto.response.QuizResultDTO;
//import com.math.mathcha.entity.Chapter;
//import com.math.mathcha.entity.Question.Question;
//import com.math.mathcha.entity.Quiz;
//import com.math.mathcha.entity.QuizHistory;
//import com.math.mathcha.entity.Topic;
//import com.math.mathcha.mapper.QuestionMapper;
//import com.math.mathcha.repository.ChapterRepository.ChapterRepository;
//import com.math.mathcha.repository.QuestionRepository.QuestionRepository;
//import com.math.mathcha.repository.QuizHistoryRepository.QuizHistoryRepository;
//import com.math.mathcha.repository.TopicRepository.TopicRepository;
//import com.math.mathcha.service.quizService.QuizService;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class QuizServiceImpl implements QuizService {
//
//    @Autowired
//    private QuizHistoryRepository quizHistoryRepository;
//    private final QuestionRepository questionRepository;
//    private final ChapterRepository chapterRepository;
//    private final TopicRepository topicRepository;
//
//    @Override
//    public List<QuestionDTO> getQuizQuestions() {
//        List<Question> questions = questionRepository.findAll();
//        List<QuestionDTO> questionDTOs = new ArrayList<>();
//
//        for (Question question : questions) {
//            questionDTOs.add(QuestionMapper.mapToQuestionDTO(question));
//
//        }
//        return questionDTOs;
//    }
//
//
//    @Override
//    public Quiz generateQuizForChapter(int chapterId, int questionPerTopic, int timeLimit) {
//        Chapter chapter = chapterRepository.findById(chapterId)
//                .orElseThrow(() -> new EntityNotFoundException("Chapter not found"));
//        List<QuestionDTO> quizQuestions = new ArrayList<>();
//        for (Topic topic : chapter.getTopics()) {
//            List<Question> questions = questionRepository.findByTopic(topic);
//            Collections.shuffle(questions);
//            List<Question> selectedQuestions = questions.subList(0, Math.min(questionPerTopic, questions.size()));
//            quizQuestions.addAll(selectedQuestions.stream()
//                    .map(QuestionMapper::mapToQuestionDTO)
//                    .collect(Collectors.toList()));
//        }
//        Quiz quiz = new Quiz();
//        quiz.setQuestions(quizQuestions);
//        quiz.setNumberOfQuestions(questionPerTopic);
//        quiz.setTimeLimit(timeLimit);
//        return quiz;
//    }
//
//    @Override
//    public Quiz generateQuizForTopic(int topicId, int numberOfQuestions, int timeLimit) {
//        Topic topic = topicRepository.findById(topicId)
//                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));
//        List<Question> questions = questionRepository.findByTopic(topic);
//        Collections.shuffle(questions);
//        List<Question> selectedQuestions = questions.subList(0, Math.min(numberOfQuestions, questions.size()));
//        List<QuestionDTO> quizQuestions = selectedQuestions.stream()
//                .map(QuestionMapper::mapToQuestionDTO)
//                .collect(Collectors.toList());
//
//        Quiz quiz = new Quiz();
//        quiz.setQuestions(quizQuestions);
//        quiz.setNumberOfQuestions(numberOfQuestions);
//        quiz.setTimeLimit(timeLimit);
//        return quiz;
//    }
//
//    @Override
//    public QuizResultDTO evaluateQuiz(Long quizId, Long userId, QuizDTO quizDTO) {
//        List<QuestionDTO> questions = quizDTO.getQuestions();
//        List<String> userAnswers = quizDTO.getUserAnswer();
//        List<Boolean> results = new ArrayList<>();
//        int correctCount = 0;
//
//        for (int i = 0; i < questions.size(); i++) {
//            QuestionDTO question = questions.get(i);
//            String userAnswer = userAnswers.get(i);
//
//            boolean isCorrect = question.getCorrectAnswer().equals(userAnswer);
//            results.add(isCorrect);
//
//            if (isCorrect) {
//                correctCount++;
//            }
//        }
//
//        QuizResultDTO quizResultDTO = new QuizResultDTO(results, correctCount, questions.size());
//
//        QuizHistory history = new QuizHistory();
//        history.setQuizId(quizId);
//        history.setUserId(userId);
//        history.setScore(correctCount);
//        history.setTimestamp(LocalDateTime.now());
//
//        quizHistoryRepository.save(history);
//        return quizResultDTO;
//    }
//
//
//}
