package com.math.mathcha.service.questionService.Impl;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.entity.Question.QuestionOption;
import com.math.mathcha.entity.Topic;
import com.math.mathcha.mapper.QuestionMapper;
import com.math.mathcha.repository.QuestionRepository.QuestionOptionRepository;
import com.math.mathcha.repository.QuestionRepository.QuestionRepository;
import com.math.mathcha.repository.TopicRepository.TopicRepository;
import com.math.mathcha.service.questionService.QuestionService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private QuestionRepository questionRepository;
    private final TopicRepository topicRepository;
private final QuestionOptionRepository questionOptionRepository;
    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO, Integer topic_id) {
        Question question = QuestionMapper.mapToQuestion(questionDTO);
        Topic topic = topicRepository.findById(topic_id)
                .orElseThrow(() -> new RuntimeException("Topic: " + topic_id + " not found"));
        question.setTopic(topic);
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
        return questions.stream()
                .map(QuestionMapper::mapToQuestionDTO)
                .collect(Collectors.toList()

        );
    }

    @Override
    public List<QuestionDTO> getQuestionsByChapterId(int chapter_id) {
        List<Question> questions = questionRepository.findQuestionsByChapterId(chapter_id);
        return questions.stream()
                .map(QuestionMapper::mapToQuestionDTO)
                .collect(Collectors.toList());
    }
    public QuestionDTO updateQuestion(Question updatedQuestion, Integer questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question with id = " + questionId + " does not exist"));

        question.setContent(updatedQuestion.getContent());
        question.setTitle(updatedQuestion.getTitle());
        question.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
        question.getQuestionOptions().clear();
        for (QuestionOption option : updatedQuestion.getQuestionOptions()) {
            QuestionOption updatedOption = questionOptionRepository.findById(option.getOption_id())
                    .orElse(new QuestionOption());
            updatedOption.setOption(option.getOption());
            updatedOption.setQuestion(question);
            question.getQuestionOptions().add(updatedOption);
        }
        Question savedQuestion = questionRepository.save(question);

        return QuestionMapper.mapToQuestionDTO(savedQuestion);
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
    public void exportQuestionsToExcel(List<QuestionDTO> questions, HttpServletResponse response) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Questions");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Content");
            headerRow.createCell(1).setCellValue("Title");
            headerRow.createCell(2).setCellValue("Option 1");
            headerRow.createCell(3).setCellValue("Option 2");
            headerRow.createCell(4).setCellValue("Option 3");
            headerRow.createCell(5).setCellValue("Option 4");
            headerRow.createCell(6).setCellValue("Correct Answer");

            int rowIndex = 1;
            for (QuestionDTO question : questions) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(question.getContent());
                row.createCell(1).setCellValue(question.getTitle());
                List<String> options = question.getOption();
                row.createCell(2).setCellValue(options.size() > 0 ? options.get(0) : "");
                row.createCell(3).setCellValue(options.size() > 1 ? options.get(1) : "");
                row.createCell(4).setCellValue(options.size() > 2 ? options.get(2) : "");
                row.createCell(5).setCellValue(options.size() > 3 ? options.get(3) : "");
                row.createCell(6).setCellValue(question.getCorrectAnswer());
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=questions.xlsx");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
