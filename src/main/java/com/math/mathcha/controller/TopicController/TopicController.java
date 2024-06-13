package com.math.mathcha.controller.TopicController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.service.chapterService.ChapterService;
import com.math.mathcha.service.topicService.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/topic")
public class TopicController {
    private TopicService topicService;
    private ChapterService chapterService;

    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @PostMapping("/chapter/{chapter_id}")
    public ResponseEntity<TopicDTO> createTopic(@PathVariable("chapter_id") Integer chapter_id,
                                                @RequestBody TopicDTO topicDTO){
        TopicDTO savedTopic = topicService.createTopic(topicDTO, chapter_id);
        return new ResponseEntity<>(savedTopic, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @GetMapping("/chapter/{chapter_id}")
    public ResponseEntity<List<TopicDTO>> getTopicByChapterId (@PathVariable("chapter_id") int chapter_id) throws IdInvalidException {
        List<TopicDTO> TopicDTOs = topicService.getTopicsByChapterId(chapter_id);
        ChapterDTO chapterDTO = chapterService.getChapterById(chapter_id);
        if (chapterDTO == null) {
            throw new IdInvalidException("Trong Chapter id = " + chapter_id + " hiện không có topic");
        }
        return ResponseEntity.ok(TopicDTOs);
    }
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @GetMapping("/{topic_id}")
    public ResponseEntity<TopicDTO> getTopicById (@PathVariable("topic_id") Integer topic_id) throws IdInvalidException {
        TopicDTO topicDTO = topicService.getTopicById(topic_id);

        if (topicDTO == null) {
            throw new IdInvalidException("Topic với id = " + topic_id + " không tồn tại");
        }
        return ResponseEntity.ok(topicDTO);
    }
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @PutMapping("/{topic_id}")
    public ResponseEntity<TopicDTO> updateTopic(@RequestBody TopicDTO updatedTopic, @PathVariable("topic_id") Integer topicId){
        TopicDTO topicDTO = topicService.updateTopic(updatedTopic, topicId );
        return ResponseEntity.ok(topicDTO);
    }
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @DeleteMapping("/{topic_id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable("topic_id") Integer topic_id) throws IdInvalidException {
        TopicDTO currentTopic = this.topicService.getTopicById(topic_id);
        if (currentTopic == null) {
            throw new IdInvalidException("Topic với id = " + topic_id + " không tồn tại");
        }

        this.chapterService.deleteChapter(topic_id);
        return ResponseEntity.ok(null);
    }


//    @GetMapping
//    public ResponseEntity<List<TopicDTO>> getTopicAll(){
//        List<TopicDTO> topic = topicService.getTopicAll();
//        return ResponseEntity.ok(topic);
//    }

}
