package com.onlinelearning.service.impl;

import com.onlinelearning.mapper.LessonMapper;
import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.response.VideoListResponseDto;
import com.onlinelearning.model.dto.response.ViewLessonResponseDto;
import com.onlinelearning.repository.CourseRepository;
import com.onlinelearning.repository.LessonRepository;
import com.onlinelearning.repository.VideoRepository;
import com.onlinelearning.service.ViewLessonService;
import com.onlinelearning.service.common.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ViewLessonServiceImpl extends BaseService implements ViewLessonService {

    private final CourseRepository courseRepository;
    private final VideoRepository videoRepository;
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    @Override
    public ResponseBase viewLesson(int courseId, String fileVideo, String name, String filePdf, Integer lessonId) {
        Map<String, Object> data = new HashMap<>();

        if (!courseRepository.isCourseExist(courseId)) {
            return new ResponseBase("redirect:/Courses", data);
        }

        setValueForHeaderFooter(data, false, true, false, false);

        List<ViewLessonResponseDto> lessons = lessonMapper.toViewLessonResponseDTOs(lessonRepository.getLessonsForManagerAndViewLesson(courseId));

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
