package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.dto.request.QuizDTO;

import com.math.mathcha.entity.Chapter;


public class ChapterMapper {
    public static ChapterDTO mapToChapterDTO(Chapter chapter){
        ChapterDTO chapterDTO = new ChapterDTO();
        chapterDTO.setChapter_id(chapter.getChapter_id());
        chapterDTO.setTitle(chapter.getTitle());
        chapterDTO.setNumber(chapter.getNumber());
        return chapterDTO;
    }

    public static Chapter mapToChapter(ChapterDTO chapterDTO){
        Chapter chapter = new Chapter();
        chapter.setChapter_id(chapterDTO.getChapter_id());
        chapter.setTitle(chapterDTO.getTitle());
        chapter.setNumber(chapterDTO.getNumber());
        return chapter;
    }
}
