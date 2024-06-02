package com.math.primarySchoolMath.service.topicService.Impl;


import com.math.primarySchoolMath.dto.request.TopicDTO;
import com.math.primarySchoolMath.entity.Topic;
import com.math.primarySchoolMath.mapper.CourseMapper;
import com.math.primarySchoolMath.mapper.TopicMapper;
import com.math.primarySchoolMath.repository.TopicRepository.TopicRepository;
import com.math.primarySchoolMath.service.topicService.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;
    @Override
    public TopicDTO createTopic(TopicDTO topicDTO) {
        Topic topic = TopicMapper.mapToLesson(topicDTO);
        Topic savedTopic = topicRepository.save(topic);
        return TopicMapper.mapToLessonDTO(savedTopic);
    }

    @Override
    public TopicDTO getTopicById(Integer topic_id) {
        Topic topic = topicRepository.findById(topic_id)
                .orElseThrow(() -> new RuntimeException("Topic "+topic_id+" not found"));
        return TopicMapper.mapToLessonDTO(topic);
    }

    @Override
    public List<TopicDTO> getTopicAll() {
        List<Topic> courses = topicRepository.findAll();
        return courses.stream().map(
                (topic) -> TopicMapper.mapToLessonDTO(topic)).collect(Collectors.toList()
        );
    }

    @Override
    public TopicDTO updateTopic(TopicDTO updateTopic, Integer topic_id) {
        Topic course = topicRepository.findById(topic_id)
                .orElseThrow(()-> new RuntimeException("Topic "+topic_id+" not found"));
        course.setTitle(updateTopic.getTitle());
        course.setNumber(updateTopic.getNumber());
        course.setIs_progress_limited(updateTopic.getIs_progress_limited());
        course.setChapter_id(updateTopic.getChapter_id());

        Topic updateTopicObj = topicRepository.save(course);
        return TopicMapper.mapToLessonDTO(updateTopicObj);
    }

    @Override
    public void deleteTopic(Integer topic_id) {
        Topic topic = topicRepository.findById(topic_id)
                .orElseThrow(() -> new RuntimeException("Not exits"+topic_id));
        topicRepository.deleteById(topic_id);
    }
}
