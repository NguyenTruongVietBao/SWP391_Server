package com.math.mathcha.controller.TopicController;

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
@RequestMapping("/topic")
public class TopicController {
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicDTO> createTopic(@RequestBody TopicDTO topicDTO){
        TopicDTO savedTopic = topicService.createTopic(topicDTO);
        return new ResponseEntity<>(savedTopic, HttpStatus.CREATED);
    }

    @GetMapping("{topic_id}")
    public ResponseEntity<TopicDTO> getTopicById (@PathVariable("topic_id") Integer topic_id){
        TopicDTO topicDTO = topicService.getTopicById(topic_id);
        return ResponseEntity.ok(topicDTO);
    }

    @GetMapping
    public ResponseEntity<List<TopicDTO>> getTopicAll(){
        List<TopicDTO> topic = topicService.getTopicAll();
        return ResponseEntity.ok(topic);
    }

    @PutMapping("/update/{topic_id}")
    public ResponseEntity<TopicDTO> updateTopic(@RequestBody TopicDTO updatedTopic, @PathVariable("topic_id") Integer topicId){
        TopicDTO topicDTO = topicService.updateTopic(updatedTopic, topicId );
        return ResponseEntity.ok(topicDTO);
    }

    @DeleteMapping("{topic_id}")
    public ResponseEntity<String> deleteTopic(@PathVariable("topic_id") Integer topic_id){
        topicService.deleteTopic(topic_id);
        return ResponseEntity.ok("Delete successfully !");
    }
}
