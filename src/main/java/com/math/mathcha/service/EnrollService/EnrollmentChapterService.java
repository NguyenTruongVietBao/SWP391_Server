package com.math.mathcha.service.EnrollService;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.EnrollmentChapterDTO;
import com.math.mathcha.entity.Chapter;
import com.math.mathcha.entity.Enrollment;
import com.math.mathcha.entity.Enrollments.EnrollmentChapter;
import com.math.mathcha.mapper.Enrollment.EnrollmentChapterMapper;
import com.math.mathcha.repository.ChapterRepository.ChapterRepository;
import com.math.mathcha.repository.EnrollRepository.EnrollmentChapterRepository;
import com.math.mathcha.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentChapterService {
    @Autowired
    private EnrollmentChapterRepository enrollmentChapterRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public EnrollmentChapterDTO createEnrollmentChapter(int enrollment_id, int chapter_id) throws IdInvalidException {
        Enrollment enrollment = enrollmentRepository.findById(enrollment_id)
                .orElseThrow(() -> new IdInvalidException("Enrollment not found"));

        Chapter chapter = chapterRepository.findById(chapter_id)
                .orElseThrow(() -> new IdInvalidException("Lesson not found"));

        EnrollmentChapter enrollmentChapter = new EnrollmentChapter();
        enrollmentChapter.setEnrollment(enrollment);
        enrollmentChapter.setChapter(chapter);
        enrollmentChapter.setIs_complete(true);

        EnrollmentChapter savedEnrollment = enrollmentChapterRepository.save(enrollmentChapter);

        return EnrollmentChapterMapper.mapToEnrollmentChapterDTO(savedEnrollment);
    }

    public Boolean getIsCompleteByEnrollmentIdAndChapterId(int enrollmentId, int chapterId) {
        return enrollmentChapterRepository.findIsCompleteByEnrollmentIdAndChapterId(enrollmentId, chapterId);
    }
}
