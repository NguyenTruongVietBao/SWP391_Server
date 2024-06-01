package com.math.primarySchoolMath.service.topicService;

import com.math.primarySchoolMath.dto.request.CourseDTO;
import com.math.primarySchoolMath.dto.request.TopicDTO;

import java.util.List;

public interface TopicService {
    TopicDTO createTopic(TopicDTO topicDTO);

    TopicDTO getTopicById ( Integer topic_id);

    List<TopicDTO> getTopicAll();

    TopicDTO updateTopic (TopicDTO topicDTO, Integer topic_id);

    void deleteTopic (Integer topic_id);
}
