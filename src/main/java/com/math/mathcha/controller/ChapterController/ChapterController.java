package com.math.mathcha.controller.ChapterController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.service.chapterService.ChapterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/course")
public class ChapterController {
    private ChapterService chapterService;
    @PostMapping("/{course_id}/chapters")
    public ResponseEntity<ChapterDTO> createChapter (@PathVariable("course_id") Integer course_id,
                                                     @RequestBody ChapterDTO chapterDTO){
        ChapterDTO savedChapter = chapterService.createChapter( chapterDTO, course_id);
        return new ResponseEntity<>(savedChapter, HttpStatus.CREATED);
    }

    @GetMapping("/{course_id}/chapters")
    public ResponseEntity<List<ChapterDTO>> getChapterByCourseId (@PathVariable("course_id") int course_id){
        List<ChapterDTO> chapterDTOs = chapterService.getChapterByCourseId(course_id);
        return ResponseEntity.ok(chapterDTOs);
    }

    @GetMapping("/{course_id}/chapters/{chapter_id}")
    public ResponseEntity<ChapterDTO> getChapterById (@PathVariable("chapter_id") Integer chapter_id){
        ChapterDTO chapterDTO = chapterService.getChapterById(chapter_id);
        return ResponseEntity.ok(chapterDTO);
    }

    @PutMapping("/{course_id}/chapters/{chapter_id}")
    public ResponseEntity<ChapterDTO> updateChapter (@RequestBody ChapterDTO updatedChapter, @PathVariable("chapter_id") Integer chapterId){
        ChapterDTO chapterDTO = chapterService.updateChapter(updatedChapter, chapterId );
        return ResponseEntity.ok(chapterDTO);
    }

    @DeleteMapping("/{course_id}/chapters/{chapter_id}")
    public ResponseEntity<String> deleteChapter(@PathVariable("chapter_id") Integer chapter_id) throws IdInvalidException{
        ChapterDTO currentUser = this.chapterService.getChapterById(chapter_id);
        if (currentUser == null) {
            throw new IdInvalidException("Chapter với id = " + chapter_id + " không tồn tại");
        }

        this.chapterService.deleteChapter(chapter_id);
        return ResponseEntity.ok(null);
    }

//    @GetMapping("/{course_id}")
//    public ResponseEntity<List<ChapterDTO>> getChapterAll(){
//        List<ChapterDTO> chapter = chapterService.getChapterAll();
//        return ResponseEntity.ok(chapter);
//    }


}
