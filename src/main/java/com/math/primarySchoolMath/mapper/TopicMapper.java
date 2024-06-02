package com.math.primarySchoolMath.mapper;

import com.math.primarySchoolMath.dto.request.LessonDTO;
import com.math.primarySchoolMath.dto.request.TopicDTO;
import com.math.primarySchoolMath.entity.Lesson;
import com.math.primarySchoolMath.entity.Topic;

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
