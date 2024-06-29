package com.math.mathcha.service.topicService.Impl;


import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.entity.Chapter;
import com.math.mathcha.entity.Course;
import com.math.mathcha.entity.Topic;
import com.math.mathcha.mapper.ChapterMapper;
import com.math.mathcha.mapper.TopicMapper;
import com.math.mathcha.repository.ChapterRepository.ChapterRepository;
import com.math.mathcha.repository.TopicRepository.TopicRepository;
import com.math.mathcha.service.chapterService.ChapterService;
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
    private ChapterService chapterService ;

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
    public TopicDTO getTopicById(Integer topic_id) throws IdInvalidException {
        Optional<Topic> topic = topicRepository.findById(topic_id);
        if (topic.isPresent()) {
            return TopicMapper.mapToTopicDTO(topic.get());
        }else{
                throw new IdInvalidException("Topic với id = " + topic_id + " không tồn tại");
        }

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
    public void deleteTopic(Integer topic_id) throws IdInvalidException {
        Topic topic = topicRepository.findById(topic_id)
                .orElseThrow(() -> new IdInvalidException("Topic với id = " + topic_id + " không tồn tại"));
        topicRepository.deleteById(topic_id);
    }

    @Override
    public List<TopicDTO> getTopicsByChapterId(int chapter_id) throws IdInvalidException {
        List<Topic> chapters = topicRepository.findTopicsByChapterId(chapter_id);
        ChapterDTO chapterDTO = chapterService.getChapterById(chapter_id);
        if (chapterDTO == null) {
            throw new IdInvalidException("Trong Chapter id = " + chapter_id + " hiện không có topic");
        }
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
