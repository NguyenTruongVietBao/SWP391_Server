package com.math.mathcha.controller.ChapterController;

import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.service.chapterService.ChapterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/chapter")
public class ChapterController {
    private ChapterService chapterService;

    @PostMapping
    public ResponseEntity<ChapterDTO> createChapter (@RequestBody ChapterDTO chapterDTO){
        ChapterDTO savedChapter = chapterService.createChapter(chapterDTO);
        return new ResponseEntity<>(savedChapter, HttpStatus.CREATED);
    }

    @GetMapping("{chapter_id}")
    public ResponseEntity<ChapterDTO> getChapterById (@PathVariable("chapter_id") Integer chapter_id){
        ChapterDTO chapterDTO = chapterService.getChapterById(chapter_id);
        return ResponseEntity.ok(chapterDTO);
    }

    @GetMapping
    public ResponseEntity<List<ChapterDTO>> getChapterAll(){
        List<ChapterDTO> chapter = chapterService.getChapterAll();
        return ResponseEntity.ok(chapter);
    }

    @PutMapping("/update/{chapter_id}")
    public ResponseEntity<ChapterDTO> updateChapter (@RequestBody ChapterDTO updatedChapter, @PathVariable("chapter_id") Integer chapterId){
        ChapterDTO chapterDTO = chapterService.updateChapter(updatedChapter, chapterId );
        return ResponseEntity.ok(chapterDTO);
    }

    @DeleteMapping("{chapter_id}")
    public ResponseEntity<String> deleteChapter(@PathVariable("chapter_id") Integer chapter_id){
        chapterService.deleteChapter(chapter_id);
        return ResponseEntity.ok("Delete successfully !");
    }
}
