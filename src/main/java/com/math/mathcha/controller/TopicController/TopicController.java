package com.math.mathcha.controller.TopicController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.service.chapterService.ChapterService;
import com.math.mathcha.service.topicService.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "api")
public class TopicController {
    private TopicService topicService;

    private ChapterService chapterService;

    @PostMapping("/chapter/{chapter_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<TopicDTO> createTopic(@PathVariable("chapter_id") Integer chapter_id,
                                                @RequestBody TopicDTO topicDTO){
        TopicDTO savedTopic = topicService.createTopic(topicDTO, chapter_id);
        return new ResponseEntity<>(savedTopic, HttpStatus.CREATED);
    }

    @GetMapping("/chapter/{chapter_id}")
    public ResponseEntity<List<TopicDTO>> getTopicByChapterId (@PathVariable("chapter_id") int chapter_id) throws IdInvalidException {
        List<TopicDTO> TopicDTOs = topicService.getTopicsByChapterId(chapter_id);
        return ResponseEntity.ok(TopicDTOs);
    }

    @GetMapping("/{topic_id}")
    @PreAuthorize("hasAnyRole('CONTENT_MANAGER', 'STUDENT','MANAGER')")
    public ResponseEntity<TopicDTO> getTopicById (@PathVariable("topic_id") Integer topic_id) throws IdInvalidException {
        TopicDTO topicDTO = topicService.getTopicById(topic_id);
        return ResponseEntity.ok(topicDTO);
    }

    @PutMapping("/{topic_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<TopicDTO> updateTopic(@RequestBody TopicDTO updatedTopic, @PathVariable("topic_id") Integer topicId){
        TopicDTO topicDTO = topicService.updateTopic(updatedTopic, topicId );
        return ResponseEntity.ok(topicDTO);
    }

    @DeleteMapping("/{topic_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<Void> deleteTopic(@PathVariable("topic_id") Integer topic_id) throws IdInvalidException {
        TopicDTO currentTopic = this.topicService.getTopicById(topic_id);
        this.chapterService.deleteChapter(topic_id);
        return ResponseEntity.ok(null);
    }


//    @GetMapping
//    public ResponseEntity<List<TopicDTO>> getTopicAll(){
//        List<TopicDTO> topic = topicService.getTopicAll();
//        return ResponseEntity.ok(topic);
//    }

}
