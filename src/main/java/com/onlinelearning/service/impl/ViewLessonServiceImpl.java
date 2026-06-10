package com.onlinelearning.service.impl;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.response.PdfListResponseDto;
import com.onlinelearning.model.dto.response.VideoListResponseDto;
import com.onlinelearning.model.dto.response.ViewLessonResponseDto;
import com.onlinelearning.repository.CourseRepository;
import com.onlinelearning.repository.LessonRepository;
import com.onlinelearning.repository.PdfRepository;
import com.onlinelearning.repository.VideoRepository;
import com.onlinelearning.service.ViewLessonService;
import com.onlinelearning.service.common.BaseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewLessonServiceImpl extends BaseService implements ViewLessonService {

    final CourseRepository courseRepository;
    final VideoRepository videoRepository;
    final LessonRepository lessonRepository;
    final PdfRepository pdfRepository;

    @Override
    public ResponseBase viewLesson(int courseId, String fileVideo, String name, String filePdf, Integer lessonId) {
        Map<String, Object> data = new HashMap<>();

        if (!courseRepository.isCourseExist(courseId)) {
            return new ResponseBase("redirect:/Courses", data);
        }

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
        data.put("pdf", filePdf == null ? "" : filePdf);
        data.put("video", fileVideo == null ? "" : fileVideo);
        data.put("name", name == null ? "" : name);
        data.put("lessonId", lessonId == null ? 0 : lessonId);
        data.put("courseId", courseId);
        return new ResponseBase("view_lesson", data);
    }
}
