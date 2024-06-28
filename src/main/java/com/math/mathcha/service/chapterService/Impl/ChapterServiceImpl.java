package com.math.mathcha.service.chapterService.Impl;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.dto.request.CourseDTO;
import com.math.mathcha.entity.Chapter;
import com.math.mathcha.entity.Course;
import com.math.mathcha.entity.User;
import com.math.mathcha.mapper.ChapterMapper;
import com.math.mathcha.mapper.UserMapper;
import com.math.mathcha.repository.ChapterRepository.ChapterRepository;
import com.math.mathcha.repository.CourseRepository.CourseRepository;
import com.math.mathcha.service.chapterService.ChapterService;
import com.math.mathcha.service.courseService.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private ChapterRepository chapterRepository;
    private CourseRepository courseRepository;
    private CourseService courseService;

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
    public ChapterDTO getChapterById(Integer chapter_id) throws IdInvalidException {
        Optional<Chapter> chapter = chapterRepository.findById(chapter_id);

        if (chapter.isPresent()) {
            return ChapterMapper.mapToChapterDTO(chapter.get());
        }else{
            throw new IdInvalidException("Chapter với id = " + chapter_id + " không tồn tại");
        }
    }

    @Override
    public ChapterDTO updateChapter(ChapterDTO updateChapter, Integer chapter_id) {
        Chapter chapter = chapterRepository.findById(chapter_id)
                .orElseThrow(()-> new RuntimeException("Chapter "+chapter_id+" not found"));
        chapter.setTitle(updateChapter.getTitle());
        chapter.setNumber(updateChapter.getNumber());
        chapter.setIs_finish(updateChapter.getIs_finish());
        Chapter updateChapterObj = chapterRepository.save(chapter);
        return ChapterMapper.mapToChapterDTO(updateChapterObj);
    }

    @Override
    public void deleteChapter(Integer chapter_id) throws IdInvalidException {
        Chapter course = chapterRepository.findById(chapter_id)
                .orElseThrow(() -> new IdInvalidException("Chapter với id = " + chapter_id + " không tồn tại"));

        chapterRepository.deleteById(chapter_id);
    }

    @Override
    public List<ChapterDTO> getChapterByCourseId(int course_id) throws IdInvalidException {
        List<Chapter> chapters = chapterRepository.findChaptersByCourseId(course_id);
        CourseDTO courseDTO = courseService.getCourseById(course_id);
        if (courseDTO == null) {
            throw new IdInvalidException("Trong course id = " + course_id + " hiện không có chapter");
        }
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
