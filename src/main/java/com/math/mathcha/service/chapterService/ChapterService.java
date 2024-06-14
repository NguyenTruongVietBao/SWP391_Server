package com.math.mathcha.service.chapterService;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.ChapterDTO;

import java.util.List;

public interface ChapterService {
    ChapterDTO createChapter(ChapterDTO chapterDTO, Integer course_id);

    ChapterDTO getChapterById ( Integer chapter_id) throws IdInvalidException;

    ChapterDTO updateChapter (ChapterDTO chapterDTO, Integer chapter_id);

    void deleteChapter (Integer chapter_id) throws IdInvalidException;

    List<ChapterDTO> getChapterByCourseId (int course_id) throws IdInvalidException;

    //    List<ChapterDTO> getChapterAll();

}
