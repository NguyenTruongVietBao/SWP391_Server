package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.dto.request.TopicForQuizDTO;
import com.math.mathcha.entity.Topic;

public class TopicMapper {


    public static TopicDTO mapToTopicDTO(Topic topic){
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setTopic_id(topic.getTopic_id());
        topicDTO.setTitle(topic.getTitle());
        topicDTO.setNumber(topic.getNumber());
        return topicDTO;
    }

    public static Topic mapToTopic(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setTopic_id(topicDTO.getTopic_id());
        topic.setTitle(topicDTO.getTitle());
        topic.setNumber(topicDTO.getNumber());
        return topic;
    }

    //dung de mapt topic cho quiz
    public static TopicForQuizDTO toToicQuizDTO(Topic topic) {
        TopicForQuizDTO tQDTO = new TopicForQuizDTO();
        tQDTO.setTopicId(topic.getTopic_id());
        tQDTO.setTitle(topic.getTitle());
        tQDTO.setNumber(topic.getNumber());
        tQDTO.setIsProgressLimited(topic.getIs_progress_limited());
        return tQDTO;
    }

    public static Topic toEntity(TopicForQuizDTO topicForQuizDTO) {
        Topic topic = new Topic();
        topic.setTopic_id(topicForQuizDTO.getTopicId());
        topic.setTitle(topicForQuizDTO.getTitle());
        topic.setNumber(topicForQuizDTO.getNumber());
        topic.setIs_progress_limited(topicForQuizDTO.getIsProgressLimited());
        return topic;
    }

}
