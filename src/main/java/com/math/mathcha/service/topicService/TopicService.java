package com.math.mathcha.service.topicService;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.dto.request.TopicDTO;

import java.util.List;

public interface TopicService {
    TopicDTO createTopic(TopicDTO topicDTO, Integer chapter_id);

    TopicDTO getTopicById ( Integer topic_id) throws IdInvalidException;

    TopicDTO updateTopic (TopicDTO topicDTO, Integer topic_id);

    void deleteTopic (Integer topic_id) throws IdInvalidException;

    List<TopicDTO> getTopicsByChapterId (int chapter_id) throws IdInvalidException;

    //    List<TopicDTO> getTopicAll();
    ChapterDTO getChapterIdByTopicId(int topic_id);
}
