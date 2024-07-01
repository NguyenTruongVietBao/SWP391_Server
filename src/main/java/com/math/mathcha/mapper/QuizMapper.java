package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.entity.Chapter;
import com.math.mathcha.entity.Course;
import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.entity.Question.QuestionOption;
import com.math.mathcha.entity.Quiz;
import com.math.mathcha.entity.Topic;
import com.math.mathcha.enums.QuizType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuizMapper {
    public static Quiz mapToQuiz(QuizDTO quizDTO) {
        Quiz quiz = new Quiz();
        quiz.setNumOfQuestions(quizDTO.getNumOfQuestions());
        quiz.setTimeLimit(quizDTO.getTimeLimit());
        quiz.setQuizType(quizDTO.getQuizType());

        // Setting references
        if (quizDTO.getCourseId() != null) {
            Course course = new Course();
            course.setCourse_id(quizDTO.getCourseId());
            quiz.setCourse(course);
        }
        if (quizDTO.getChapterId() != null) {
            Chapter chapter = new Chapter();
            chapter.setChapter_id(quizDTO.getChapterId());
            quiz.setChapter(chapter);
        }
        if (quizDTO.getTopicId() != null) {
            Topic topic = new Topic();
            topic.setTopic_id(quizDTO.getTopicId());
            quiz.setTopic(topic);
        }

        quiz.setQuestions(quizDTO.getQuestions().stream()
                .map(QuestionMapper::mapToQuestion)
                .collect(Collectors.toList()));

        return quiz;
    }

    public static QuizDTO mapToQuizDTO(Quiz quiz) {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setQuizId(quiz.getQuiz_id());
        quizDTO.setNumOfQuestions(quiz.getNumOfQuestions());
        quizDTO.setTimeLimit(quiz.getTimeLimit());
        quizDTO.setQuizType(quiz.getQuizType());

        if (quiz.getCourse() != null) {
            quizDTO.setCourseId(quiz.getCourse().getCourse_id());
        }
        if (quiz.getChapter() != null) {
            quizDTO.setChapterId(quiz.getChapter().getChapter_id());
        }
        if (quiz.getTopic() != null) {
            quizDTO.setTopicId(quiz.getTopic().getTopic_id());
        }

        quizDTO.setQuestions(quiz.getQuestions().stream()
                .map(QuestionMapper::mapToQuestionDTO)
                .collect(Collectors.toList()));
        return quizDTO;
    }
}


