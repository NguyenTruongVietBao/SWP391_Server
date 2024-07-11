package com.math.mathcha.controller.QuestionController;


import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.mapper.QuestionMapper;
import com.math.mathcha.service.questionService.QuestionService;
import com.math.mathcha.service.topicService.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/questions")
@SecurityRequirement(name = "api")
public class QuestionController {
    private final QuestionService questionService;
    private final TopicService topicService;
  //  @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @PostMapping("/{topic_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO questionDTO,
                                                      @PathVariable("topic_id") Integer topic_id) {
        QuestionDTO saveQuestion = questionService.createQuestion(questionDTO, topic_id);
        return new ResponseEntity<>(saveQuestion, HttpStatus.CREATED);
    }
   // @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @GetMapping("/topic/{topic_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByTopicId(@PathVariable("topic_id") int topic_id) throws IdInvalidException {
        List<QuestionDTO> question = questionService.getQuestionsByTopicId(topic_id);
        TopicDTO topicDTO = topicService.getTopicById(topic_id);
        if (topicDTO == null) {
            throw new IdInvalidException("Trong topic id = " + topic_id + " hiện không có Question");
        }
        return ResponseEntity.ok(question);
    }

//    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @GetMapping("/{question_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<QuestionDTO> getQuestionById (@PathVariable("question_id") Integer question_id) throws IdInvalidException {
        QuestionDTO questionDTO = questionService.getQuestionById(question_id);

        if (questionDTO == null) {
            throw new IdInvalidException("Question với id = " + question_id + " không tồn tại");
        }
        return ResponseEntity.ok(questionDTO);
    }

   // @PreAuthorize("hasRole('CONTENT_MANAGER')")
   @PutMapping("/{question_id}")
   @PreAuthorize("hasRole('CONTENT_MANAGER')")
   public ResponseEntity<QuestionDTO> updateQuestion(@RequestBody QuestionDTO updatedQuestionDTO, @PathVariable("question_id") Integer questionId) {
       Question updatedQuestion = QuestionMapper.mapToQuestion(updatedQuestionDTO);
       QuestionDTO questionDTO = questionService.updateQuestion(updatedQuestion, questionId);
       return ResponseEntity.ok(questionDTO);
   }

  //     @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @DeleteMapping("/{question_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("question_id") Integer question_id) throws IdInvalidException {
        QuestionDTO currentTopic = this.questionService.getQuestionById(question_id);
        if (currentTopic == null) {
            throw new IdInvalidException("Question với id = " + question_id + " không tồn tại");
        }

        this.questionService.deleteQuestion(question_id);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/upload/{topic_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<String> uploadQuestionsFromExcel(@RequestParam("file") MultipartFile file, @PathVariable("topic_id") Integer topicId) {
        List<QuestionDTO> questions = new ArrayList<>();

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next(); // Skip header row if any
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                QuestionDTO questionDTO = new QuestionDTO();
                questionDTO.setContent(getCellValueAsString(row.getCell(0)));
                questionDTO.setTitle(getCellValueAsString(row.getCell(1)));
                questionDTO.setOption(Arrays.asList(
                        getCellValueAsString(row.getCell(2)),
                        getCellValueAsString(row.getCell(3)),
                        getCellValueAsString(row.getCell(4)),
                        getCellValueAsString(row.getCell(5))
                ));
                questionDTO.setCorrectAnswer(getCellValueAsString(row.getCell(6)));
                questions.add(questionDTO);
            }

            questionService.saveQuestionsFromExcel(questions, topicId);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to parse the file");
        }

        return ResponseEntity.ok("File uploaded and questions saved");
    }

    private String getCellValueAsString(Cell cell) {
        return cell != null ? cell.toString() : "";
    }

    @GetMapping("/export/{topic_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')") // can xem lai
    public void exportQuestionsToExcel(HttpServletResponse response, @PathVariable("topic_id") Integer topicId) {
        List<QuestionDTO> questions = questionService.getQuestionsByTopicId(topicId);
        questionService.exportQuestionsToExcel(questions, response);
    }
}
