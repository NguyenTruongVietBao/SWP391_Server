package com.math.mathcha.controller.QuestionController;


import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.service.questionService.QuestionService;
import com.math.mathcha.service.topicService.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final TopicService topicService;
  //  @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @PostMapping("/{topic_id}")
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO questionDTO,
                                                      @PathVariable("topic_id") Integer topic_id) {
        QuestionDTO saveQuestion = questionService.createQuestion(questionDTO, topic_id);
        return new ResponseEntity<>(saveQuestion, HttpStatus.CREATED);
    }
   // @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @GetMapping("/topic/{topic_id}")
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
    public ResponseEntity<QuestionDTO> getQuestionById (@PathVariable("question_id") Integer question_id) throws IdInvalidException {
        QuestionDTO questionDTO = questionService.getQuestionById(question_id);

        if (questionDTO == null) {
            throw new IdInvalidException("Question với id = " + question_id + " không tồn tại");
        }
        return ResponseEntity.ok(questionDTO);
    }

   // @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @PutMapping("/{question_id}")
    public ResponseEntity<QuestionDTO> updateQuestion (@RequestBody QuestionDTO updatedQuestion, @PathVariable("question_id") Integer questionId){
        QuestionDTO questionDTO = questionService.updateQuestion(updatedQuestion, questionId );
        return ResponseEntity.ok(questionDTO);
    }

  //     @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @DeleteMapping("/{question_id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("question_id") Integer question_id) throws IdInvalidException {
        QuestionDTO currentTopic = this.questionService.getQuestionById(question_id);
        if (currentTopic == null) {
            throw new IdInvalidException("Question với id = " + question_id + " không tồn tại");
        }

        this.questionService.deleteQuestion(question_id);
        return ResponseEntity.ok(null);
    }
}
