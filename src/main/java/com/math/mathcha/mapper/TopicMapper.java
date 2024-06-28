package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.entity.Topic;

public class TopicMapper {
    public static TopicDTO mapToTopicDTO(Topic topic){
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setTopic_id(topic.getTopic_id());
        topicDTO.setTitle(topic.getTitle());
        topicDTO.setNumber(topic.getNumber());
        topicDTO.setIs_progress_limited(topic.getIs_progress_limited());
        topicDTO.setIs_finish(topic.getIs_finish());
        return topicDTO;
    }

    public static Topic mapToTopic(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setTopic_id(topicDTO.getTopic_id());
        topic.setTitle(topicDTO.getTitle());
        topic.setNumber(topicDTO.getNumber());
        topic.setIs_progress_limited(topicDTO.getIs_progress_limited());
        topic.setIs_finish(topicDTO.getIs_finish());
        return topic;
    }

}
