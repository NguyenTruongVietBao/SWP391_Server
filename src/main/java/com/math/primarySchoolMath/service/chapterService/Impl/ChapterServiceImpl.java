package com.math.primarySchoolMath.service.chapterService.Impl;

import com.math.primarySchoolMath.dto.request.ChapterDTO;
import com.math.primarySchoolMath.entity.Chapter;
import com.math.primarySchoolMath.mapper.ChapterMapper;
import com.math.primarySchoolMath.repository.ChapterRepository.ChapterRepository;
import com.math.primarySchoolMath.service.chapterService.ChapterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private ChapterRepository chapterRepository;
    @Override
    public ChapterDTO createChapter(ChapterDTO chapterDTO) {
        Chapter chapter = ChapterMapper.mapToChapter(chapterDTO);
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
    public List<ChapterDTO> getChapterAll() {
        List<Chapter> chapters = chapterRepository.findAll();
        return chapters.stream().map(
                (chapter) -> ChapterMapper.mapToChapterDTO(chapter)).collect(Collectors.toList()
        );
    }

    @Override
    public ChapterDTO updateChapter(ChapterDTO updateChapter, Integer chapter_id) {
        Chapter chapter = chapterRepository.findById(chapter_id)
                .orElseThrow(()-> new RuntimeException("Chapter "+chapter_id+" not found"));
        chapter.setTitle(updateChapter.getTitle());
        chapter.setNumber(updateChapter.getNumber());
        chapter.setCourse_id(updateChapter.getCourse_id());
        Chapter updateChapterObj = chapterRepository.save(chapter);
        return ChapterMapper.mapToChapterDTO(updateChapterObj);
    }

    @Override
    public void deleteChapter(Integer chapter_id) {
        Chapter course = chapterRepository.findById(chapter_id)
                .orElseThrow(() -> new RuntimeException("Not exits"+chapter_id));
        chapterRepository.deleteById(chapter_id);
    }
}
