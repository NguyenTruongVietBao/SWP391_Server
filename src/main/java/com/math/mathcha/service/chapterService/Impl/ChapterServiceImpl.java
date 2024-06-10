package com.math.mathcha.service.chapterService.Impl;

import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.entity.Chapter;
import com.math.mathcha.entity.Course;
import com.math.mathcha.mapper.ChapterMapper;
import com.math.mathcha.repository.ChapterRepository.ChapterRepository;
import com.math.mathcha.repository.CourseRepository.CourseRepository;
import com.math.mathcha.service.chapterService.ChapterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private ChapterRepository chapterRepository;
    private CourseRepository courseRepository;

    @Override
    public ChapterDTO createChapter( ChapterDTO chapterDTO, Integer course_id) {
        Chapter chapter = ChapterMapper.mapToChapter(chapterDTO);
        Course course = courseRepository.findById(course_id)
                .orElseThrow(() -> new RuntimeException("Course: "+course_id+" not found"));
        chapter.setCourse(course);
        Chapter savedChapter = chapterRepository.save(chapter);
        return ChapterMapper.mapToChapterDTO(savedChapter);
    }

    @Override
    public ChapterDTO getChapterById(Integer chapter_id) {
        Chapter chapter = chapterRepository.findById(chapter_id)
                .orElseThrow(() -> new RuntimeException("Chapter "+chapter_id+" not found"));
        return ChapterMapper.mapToChapterDTO(chapter);
    }

    @Override
    public ChapterDTO updateChapter(ChapterDTO updateChapter, Integer chapter_id) {
        Chapter chapter = chapterRepository.findById(chapter_id)
                .orElseThrow(()-> new RuntimeException("Chapter "+chapter_id+" not found"));
        chapter.setTitle(updateChapter.getTitle());
        chapter.setNumber(updateChapter.getNumber());
        Chapter updateChapterObj = chapterRepository.save(chapter);
        return ChapterMapper.mapToChapterDTO(updateChapterObj);
    }

    @Override
    public void deleteChapter(Integer chapter_id) {
        Chapter course = chapterRepository.findById(chapter_id)
                .orElseThrow(() -> new RuntimeException("Not exits"+chapter_id));
        chapterRepository.deleteById(chapter_id);
    }

    @Override
    public List<ChapterDTO> getChapterByCourseId(int course_id) {
        List<Chapter> chapters = chapterRepository.findChaptersByCourseId(course_id);
        return chapters.stream().map(
                (chapter) -> ChapterMapper.mapToChapterDTO(chapter)).collect(Collectors.toList()
        );
    }

//    @Override
//    public List<ChapterDTO> getChapterAll() {
//        List<Chapter> chapters = chapterRepository.findAll();
//        return chapters.stream().map(
//                (chapter) -> ChapterMapper.mapToChapterDTO(chapter)).collect(Collectors.toList()
//        );
//    }
}
