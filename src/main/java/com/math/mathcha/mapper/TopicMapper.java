package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.entity.Topic;

public class TopicMapper {
    public static TopicDTO mapToLessonDTO(Topic topic){
        return new TopicDTO(
                topic.getTopic_id(),
                topic.getTitle(),
                topic.getNumber(),
                topic.getIs_progress_limited(),
                topic.getChapter_id()

        );
    }

    public static Topic mapToLesson(TopicDTO topicDTO){
        return new Topic(
                topicDTO.getTopic_id(),
                topicDTO.getTitle(),
                topicDTO.getNumber(),
                topicDTO.getIs_progress_limited(),
                topicDTO.getChapter_id()
        );
    }
}
