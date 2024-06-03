package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.ChapterDTO;

import com.math.mathcha.entity.Chapter;


public class ChapterMapper {
    public static ChapterDTO mapToChapterDTO(Chapter chapter){
        return new ChapterDTO(
                chapter.getChapter_id(),
                chapter.getTitle(),
                chapter.getNumber(),
                chapter.getCourse_id()
        );
    }

    public static Chapter mapToChapter(ChapterDTO chapterDTO){
        return new Chapter(
                chapterDTO.getChapter_id(),
                chapterDTO.getTitle(),
                chapterDTO.getNumber(),
                chapterDTO.getCourse_id()
        );
    }
}