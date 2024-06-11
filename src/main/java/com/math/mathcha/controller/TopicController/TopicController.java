package com.math.mathcha.controller.TopicController;

import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.service.topicService.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("course/{course_id}/chapters")
public class TopicController {
    private TopicService topicService;

    @PostMapping("{chapter_id}/topics")
    public ResponseEntity<TopicDTO> createTopic(@PathVariable("chapter_id") Integer chapter_id,
                                                @RequestBody TopicDTO topicDTO){
        TopicDTO savedTopic = topicService.createTopic(topicDTO, chapter_id);
        return new ResponseEntity<>(savedTopic, HttpStatus.CREATED);
    }

    @GetMapping("{chapter_id}/topics")
    public ResponseEntity<List<TopicDTO>> getTopicByChapterId (@PathVariable("chapter_id") int chapter_id){
        List<TopicDTO> TopicDTOs = topicService.getTopicsByChapterId(chapter_id);
        return ResponseEntity.ok(TopicDTOs);
    }

    @GetMapping("{chapter_id}/topics/{topic_id}")
    public ResponseEntity<TopicDTO> getTopicById (@PathVariable("topic_id") Integer topic_id){
        TopicDTO topicDTO = topicService.getTopicById(topic_id);
        return ResponseEntity.ok(topicDTO);
    }

    @PutMapping("{chapter_id}/topics/{topic_id}")
    public ResponseEntity<TopicDTO> updateTopic(@RequestBody TopicDTO updatedTopic, @PathVariable("topic_id") Integer topicId){
        TopicDTO topicDTO = topicService.updateTopic(updatedTopic, topicId );
        return ResponseEntity.ok(topicDTO);
    }

    @DeleteMapping("{chapter_id}/topics/{topic_id}")
    public ResponseEntity<String> deleteTopic(@PathVariable("topic_id") Integer topic_id){
        topicService.deleteTopic(topic_id);
        return ResponseEntity.ok("Delete successfully !");
    }


//    @GetMapping
//    public ResponseEntity<List<TopicDTO>> getTopicAll(){
//        List<TopicDTO> topic = topicService.getTopicAll();
//        return ResponseEntity.ok(topic);
//    }

}
