package com.math.mathcha.service.topicService.Impl;


import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.entity.Chapter;
import com.math.mathcha.entity.Topic;
import com.math.mathcha.mapper.TopicMapper;
import com.math.mathcha.repository.ChapterRepository.ChapterRepository;
import com.math.mathcha.repository.TopicRepository.TopicRepository;
import com.math.mathcha.service.topicService.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;
    private final ChapterRepository chapterRepository;

    @Override
    public TopicDTO createTopic(TopicDTO topicDTO, Integer chapter_id) {
        Topic topic = TopicMapper.mapToTopic(topicDTO);
        Chapter chapter = chapterRepository.findById(chapter_id)
                .orElseThrow(() -> new RuntimeException("Chapter: "+chapter_id+" not found"));
        topic.setChapter(chapter);
        Topic savedTopic = topicRepository.save(topic);
        return TopicMapper.mapToTopicDTO(savedTopic);
    }

    @Override
    public TopicDTO getTopicById(Integer topic_id) {
        Optional<Topic> topic = topicRepository.findById(topic_id);
        if (topic.isPresent()) {
            return TopicMapper.mapToTopicDTO(topic.get());
        }
        return null;
    }


    @Override
    public TopicDTO updateTopic(TopicDTO updateTopic, Integer topic_id) {
        Topic course = topicRepository.findById(topic_id)
                .orElseThrow(()-> new RuntimeException("Topic "+topic_id+" not found"));
        course.setTitle(updateTopic.getTitle());
        course.setNumber(updateTopic.getNumber());
        course.setIs_progress_limited(updateTopic.getIs_progress_limited());
        Topic updateTopicObj = topicRepository.save(course);
        return TopicMapper.mapToTopicDTO(updateTopicObj);
    }

    @Override
    public void deleteTopic(Integer topic_id) {
        Topic topic = topicRepository.findById(topic_id)
                .orElseThrow(() -> new RuntimeException("Not exits"+topic_id));
        topicRepository.deleteById(topic_id);
    }

    @Override
    public List<TopicDTO> getTopicsByChapterId(int chapter_id) {
        List<Topic> chapters = topicRepository.findTopicsByChapterId(chapter_id);
        return chapters.stream().map(
                (topic) -> TopicMapper.mapToTopicDTO(topic)).collect(Collectors.toList()
        );
    }

//    @Override
//    public List<TopicDTO> getTopicAll() {
//        List<Topic> courses = topicRepository.findAll();
//        return courses.stream().map(
//                (topic) -> TopicMapper.mapToTopicDTO(topic)).collect(Collectors.toList()
//        );
//    }
}
