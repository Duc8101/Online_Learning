package com.onlinelearning.service.impl;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.LessonCreateRequestDto;
import com.onlinelearning.model.dto.request.LessonUpdateRequestDto;
import com.onlinelearning.model.dto.response.PdfListResponseDto;
import com.onlinelearning.model.dto.response.VideoListResponseDto;
import com.onlinelearning.model.dto.response.ViewLessonResponseDto;
import com.onlinelearning.model.entity.Lesson;
import com.onlinelearning.repository.CourseRepository;
import com.onlinelearning.repository.LessonRepository;
import com.onlinelearning.repository.PdfRepository;
import com.onlinelearning.repository.VideoRepository;
import com.onlinelearning.service.ManagerLessonService;
import com.onlinelearning.service.common.BaseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerLessonServiceImpl extends BaseService implements ManagerLessonService {

    final CourseRepository courseRepository;
    final VideoRepository videoRepository;
    final LessonRepository lessonRepository;
    final PdfRepository pdfRepository;

    static final int MAX_LESSON_NAME = 200;

    @Override
    public ResponseBase list(int courseId, String fileVideo, String name, String filePdf, Integer lessonId, long userId) {
        Map<String, Object> data = new HashMap<>();
        if (!courseRepository.checkCourseTeacherExist(courseId, userId)) {
            return new ResponseBase("redirect:/ManagerCourse", data);
        }

        setData(data, courseId, fileVideo, name, filePdf, lessonId);
        return new ResponseBase("manager_lesson/list", data);
    }

    private void setData(Map<String, Object> data, int courseId, String fileVideo, String name, String filePdf, Integer lessonId) {
        setValueForHeaderFooter(data, false, true, false, false);
        List<ViewLessonResponseDto> lessons = lessonRepository.getLessonsForManagerAndViewLesson(courseId);

        if (!lessons.isEmpty()) {
            List<Integer> lessonIds = lessons.stream().map(ViewLessonResponseDto::getLessonId).toList();

            List<VideoListResponseDto> videoList = videoRepository.getVideosByLessonIds(lessonIds);
            Map<Integer, List<VideoListResponseDto>> videoMap = videoList.stream()
                    .collect(Collectors.groupingBy(VideoListResponseDto::getLessonId));

            List<PdfListResponseDto> pdfs = pdfRepository.getPdfsByLessonIds(lessonIds);
            Map<Integer, List<PdfListResponseDto>> pdfMap = pdfs.stream()
                    .collect(Collectors.groupingBy(PdfListResponseDto::getLessonId));

            for (ViewLessonResponseDto lesson : lessons) {
                lesson.setVideos(videoMap.getOrDefault(lesson.getLessonId(), Collections.emptyList()));
                lesson.setPdfs(pdfMap.getOrDefault(lesson.getLessonId(), Collections.emptyList()));
            }
        }

        // if start to manager lesson
        if (fileVideo == null && filePdf == null) {
            List<VideoListResponseDto> videos = videoRepository.getVideosOfCourse(courseId, PageRequest.of(0, 1));
            if (!videos.isEmpty()) {
                fileVideo = videos.getFirst().getFileVideo();
                name = videos.getFirst().getVideoName();
                lessonId = videos.getFirst().getLessonId();
            }
        }

        data.put("lessons", lessons);
        data.put("filePdf", filePdf == null ? "" : filePdf);
        data.put("fileVideo", fileVideo == null ? "" : fileVideo);
        data.put("name", name == null ? "" : name);
        data.put("lessonId", lessonId == null ? 0 : lessonId);
        data.put("courseId", courseId);
    }

    @Override
    public ResponseBase create(LessonCreateRequestDto DTO) {
        Map<String, Object> data = new HashMap<>();

        setData(data, DTO.getCourseId(), null, null, null, null);

        if (DTO.getLessonName().trim().isEmpty()) {
            data.put("error", "Lesson name not empty");
            return new ResponseBase("manager_lesson/list", data);
        }

        if (DTO.getLessonName().trim().length() > MAX_LESSON_NAME) {
            data.put("error", String.format("Lesson name max %d characters", MAX_LESSON_NAME));
            return new ResponseBase("manager_lesson/list", data);
        }

        if (lessonRepository.isLessonExist(DTO.getLessonName().trim(), DTO.getCourseId())) {
            data.put("error", "Lesson already exists");
            return new ResponseBase("manager_lesson/list", data);
        }

        Lesson lesson = Lesson.builder()
                .lessonName(DTO.getLessonName().trim())
                .courseId(DTO.getCourseId())
                .build();

        lessonRepository.save(lesson);
        return new ResponseBase("redirect:/ManagerLesson/" + DTO.getCourseId(), new HashMap<>());
    }

    @Override
    public ResponseBase update(int lessonId, LessonUpdateRequestDto DTO) {
        Map<String, Object> data = new HashMap<>();
        Integer courseId = lessonRepository.getCourseId(lessonId);
        if (courseId == null) {
            setValueForHeaderFooter(data, true, true, true, true);
            data.put("error", "Lesson not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        setData(data, courseId, null, null, null, null);
        if (DTO.getLessonName().trim().isEmpty()) {
            data.put("error", "Lesson name not empty");
            return new ResponseBase("manager_lesson/list", data);
        }

        if (DTO.getLessonName().trim().length() > MAX_LESSON_NAME) {
            data.put("error", String.format("Lesson name max %d characters", MAX_LESSON_NAME));
            return new ResponseBase("manager_lesson/list", data);
        }

        if (lessonRepository.isLessonExist(DTO.getLessonName().trim(), courseId, lessonId)) {
            data.put("error", "Lesson already exists");
            return new ResponseBase("manager_lesson/list", data);
        }

        lessonRepository.updateLesson(DTO.getLessonName().trim(), Instant.now(), lessonId);
        return new ResponseBase("redirect:/ManagerLesson/" + courseId, data);
    }

    @Override
    public ResponseBase delete(int lessonId) {
        Map<String, Object> data = new HashMap<>();

        Integer courseId = lessonRepository.getCourseId(lessonId);
        if (courseId == null) {
            setValueForHeaderFooter(data, true, true, true, true);
            data.put("error", "Lesson not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        lessonRepository.deleteById(lessonId);
        return new ResponseBase("redirect:/ManagerLesson/" + courseId, data);
    }
}
