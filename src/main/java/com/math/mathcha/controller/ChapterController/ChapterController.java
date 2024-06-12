package com.math.mathcha.controller.ChapterController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.dto.request.CourseDTO;
import com.math.mathcha.service.chapterService.ChapterService;
import com.math.mathcha.service.courseService.CourseService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/chapter")
public class ChapterController {
    private static final Logger log = LoggerFactory.getLogger(ChapterController.class);
    private ChapterService chapterService;
    private CourseService courseService;


    @PostMapping("/{course_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<ChapterDTO> createChapter (@PathVariable("course_id") Integer course_id,
                                                     @RequestBody ChapterDTO chapterDTO){
        ChapterDTO savedChapter = chapterService.createChapter( chapterDTO, course_id);
        return new ResponseEntity<>(savedChapter, HttpStatus.CREATED);
    }


    @GetMapping("/course/{course_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<List<ChapterDTO>> getChapterByCourseId (@PathVariable("course_id") int course_id) throws IdInvalidException {
        List<ChapterDTO> chapterDTOs = chapterService.getChapterByCourseId(course_id);
        CourseDTO courseDTO = courseService.getCourseById(course_id);
        if (courseDTO == null) {
            throw new IdInvalidException("Trong course id = " + course_id + " hiện không có chapter");
        }
        return ResponseEntity.ok(chapterDTOs);
    }


    @GetMapping("/{chapter_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<ChapterDTO> getChapterById (@PathVariable("chapter_id") Integer chapter_id) throws IdInvalidException {
        ChapterDTO chapterDTO = chapterService.getChapterById(chapter_id);

        if (chapterDTO == null) {
            throw new IdInvalidException("Chapter với id = " + chapter_id + " không tồn tại");
        }
        return ResponseEntity.ok(chapterDTO);
    }

    @PutMapping("/{chapter_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<ChapterDTO> updateChapter (@RequestBody ChapterDTO updatedChapter, @PathVariable("chapter_id") Integer chapterId){
        ChapterDTO chapterDTO = chapterService.updateChapter(updatedChapter, chapterId );
        return ResponseEntity.ok(chapterDTO);
    }


    @DeleteMapping("/{chapter_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<Void> deleteChapter(@PathVariable("chapter_id") Integer chapter_id) throws IdInvalidException{
        ChapterDTO currentChapter = this.chapterService.getChapterById(chapter_id);
        if (currentChapter == null) {
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
