package com.math.mathcha.controller.ChapterController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.dto.request.CourseDTO;
import com.math.mathcha.service.chapterService.ChapterService;
import com.math.mathcha.service.courseService.CourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "api")
public class ChapterController {
    private static final Logger log = LoggerFactory.getLogger(ChapterController.class);
    private ChapterService chapterService;
    private CourseService courseService;

    @PostMapping("/{course_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<ChapterDTO> createChapter(@PathVariable("course_id") Integer course_id,
                                                    @RequestBody ChapterDTO chapterDTO) {
        ChapterDTO savedChapter = chapterService.createChapter(chapterDTO, course_id);
        return new ResponseEntity<>(savedChapter, HttpStatus.CREATED);
    }

    @GetMapping("/course/{course_id}")

    public ResponseEntity<List<ChapterDTO>> getChapterByCourseId(@PathVariable("course_id") int course_id) throws IdInvalidException {
        List<ChapterDTO> chapterDTOs = chapterService.getChapterByCourseId(course_id);
        return ResponseEntity.ok(chapterDTOs);
    }

    @GetMapping("/{chapter_id}")
    @PreAuthorize("hasAnyRole('CONTENT_MANAGER', 'STUDENT','MANAGER')")
    public ResponseEntity<ChapterDTO> getChapterById(@PathVariable("chapter_id") Integer chapter_id) throws IdInvalidException {
        ChapterDTO chapterDTO = chapterService.getChapterById(chapter_id);
        return ResponseEntity.ok(chapterDTO);
    }

    @PutMapping("/{chapter_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<ChapterDTO> updateChapter(@RequestBody ChapterDTO updatedChapter, @PathVariable("chapter_id") Integer chapterId) {
        ChapterDTO chapterDTO = chapterService.updateChapter(updatedChapter, chapterId);
        return ResponseEntity.ok(chapterDTO);
    }

    @DeleteMapping("/{chapter_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<Void> deleteChapter(@PathVariable("chapter_id") Integer chapter_id) throws IdInvalidException {
        ChapterDTO currentChapter = this.chapterService.getChapterById(chapter_id);

        this.chapterService.deleteChapter(chapter_id);
        return ResponseEntity.ok(null);
    }
}

//    @GetMapping("/{course_id}")
//    public ResponseEntity<List<ChapterDTO>> getChapterAll(){
//        List<ChapterDTO> chapter = chapterService.getChapterAll();
//        return ResponseEntity.ok(chapter);
//    }



