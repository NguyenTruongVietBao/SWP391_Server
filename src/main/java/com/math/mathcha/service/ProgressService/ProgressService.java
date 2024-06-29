package com.math.mathcha.service.ProgressService;

import com.math.mathcha.Util.AccountUtil;
import com.math.mathcha.dto.request.CreateProgressDTO;
import com.math.mathcha.entity.*;
import com.math.mathcha.repository.ChapterRepository.ChapterRepository;
import com.math.mathcha.repository.CourseRepository.CourseRepository;
import com.math.mathcha.repository.LessonRepository.LessonRepository;
import com.math.mathcha.repository.ProgressRepository.ProgressRepository;
import com.math.mathcha.repository.TopicRepository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;
    @Autowired
        private CourseRepository courseRepository;
    @Autowired
        private ChapterRepository chapterRepository;
    @Autowired
        private TopicRepository topicRepository;
    @Autowired
        private AccountUtil accountUtil;
    @Autowired
        private LessonRepository lessonRepository;


    public Progress createProgress(CreateProgressDTO createProgressDTO) {
        if (progressRepository.findProgressByLessonIdAndStudentId(createProgressDTO.getLession_id(), accountUtil.getCurrentStudent().getStudent_id()) != null){
            return null;
        }
        Progress progress = new Progress();
        Lesson lesson = lessonRepository.findLessonById(createProgressDTO.getLession_id());
        progress.setLesson(lesson);
        progress.setCreateDate(new Date());
        progress.setCourse_id(lesson.getTopic().getChapter().getCourse().getCourse_id());
        progress.setStudent(accountUtil.getCurrentStudent());
        return progressRepository
                .save(progress);
    }

    public double percentCourse(int course_id, int chapter_id) {
        double percentage = 0;
        List<Progress> progress = progressRepository.findProgressByStudentId(accountUtil.getCurrentStudent().getStudent_id());
        if (progress != null) {
            int numOfLessons = 0;
            int numOfLessonDone = 0;
            List<Chapter> chapters = chapterRepository.findChaptersByCourseId(course_id);
            List<Lesson> lessons = new ArrayList<>();

            for (Chapter chapter : chapters) {
                if (chapter.getChapter_id() == chapter_id) {
                    List<Topic> topics = topicRepository.findTopicsByChapterId(chapter_id);

                    for (Topic topic : topics) {
                        List<Lesson> topicLessons = lessonRepository.findLessonsByTopicId(topic.getTopic_id());
                        numOfLessons += topicLessons.size();
                        lessons.addAll(topicLessons);
                    }
                    break;
                }
            }

            for (Lesson lesson : lessons) {
                for (Progress progress1 : progress) {
                    if (progress1.getLesson().getLesson_id() == lesson.getLesson_id()) {
                        numOfLessonDone++;
                    }
                }
            }

            if (numOfLessons > 0) {
                percentage = (double) numOfLessonDone / numOfLessons * 100;
            }
        }
        return percentage;
    }

    public Progress getProgressByLessonIdAndStudentId(int lesson_id) {
        return progressRepository.findProgressByLessonIdAndStudentId(lesson_id, accountUtil.getCurrentStudent().getStudent_id());
    }


}
