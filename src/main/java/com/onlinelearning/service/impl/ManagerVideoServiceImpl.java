package com.onlinelearning.service.impl;

import com.onlinelearning.mapper.LessonMapper;
import com.onlinelearning.mapper.VideoMapper;
import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.VideoCreateRequestDto;
import com.onlinelearning.model.dto.request.VideoUpdateRequestDto;
import com.onlinelearning.model.dto.response.VideoListResponseDto;
import com.onlinelearning.model.dto.response.VideoUpdateResponseDto;
import com.onlinelearning.model.dto.response.ViewLessonResponseDto;
import com.onlinelearning.model.entity.Lesson;
import com.onlinelearning.model.entity.Video;
import com.onlinelearning.repository.LessonRepository;
import com.onlinelearning.repository.VideoRepository;
import com.onlinelearning.service.ManagerVideoService;
import com.onlinelearning.service.common.BaseService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerVideoServiceImpl extends BaseService implements ManagerVideoService {

    final LessonRepository lessonRepository;
    final VideoRepository videoRepository;
    final LessonMapper lessonMapper;
    final VideoMapper videoMapper;

    private void setData(Map<String, Object> data, int courseId, String fileVideo,  String name, Integer lessonId) {
        setValueForHeaderFooter(data, false, true, false, false);

        List<ViewLessonResponseDto> lessons = lessonMapper.toViewLessonResponseDTOs(lessonRepository.getLessonsForManagerAndViewLesson(courseId));
        if (fileVideo == null) {
            List<VideoListResponseDto> videos = videoRepository.getVideosOfCourse(courseId, PageRequest.of(0, 1));
            if (!videos.isEmpty()) {
                fileVideo = videos.getFirst().getFileVideo();
                name = videos.getFirst().getVideoName();
                lessonId = videos.getFirst().getLessonId();
            }
        }

        data.put("lessons", lessons);
        data.put("pdf", "");
        data.put("video", fileVideo == null ? "" : fileVideo);
        data.put("name", name);
        data.put("lessonId", lessonId == null ? 0 : lessonId);
        data.put("courseId", courseId);
    }

    @Override
    public ResponseBase create(VideoCreateRequestDto DTO) {
        Map<String, Object> data = new HashMap<>();
        Lesson lesson = lessonRepository.getByLessonId(DTO.getLessonId());
        if (lesson == null) {
            setValueForHeaderFooter(data, true, true, true, true);
            data.put("error", "Lesson not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        if (DTO.getVideoName().trim().isEmpty()) {
            setData(data, lesson.getCourse().getCourseId(), null, null, DTO.getLessonId());
            data.put("error", "Video name not empty");
            return new ResponseBase("manager_lesson/list", data);
        }

        Video video = videoMapper.toVideo(DTO);
        video.setLesson(lesson);
        videoRepository.save(video);
        return new ResponseBase(String.format("redirect:/ManagerLesson/%d?lessonId=%d&name=%s&video=%s", lesson.getCourse().getCourseId(), DTO.getLessonId(), DTO.getVideoName().trim(), DTO.getFileVideo()), data);
    }

    @Override
    public ResponseBase update(int videoId, VideoUpdateRequestDto DTO) {
        Map<String, Object> data = new HashMap<>();

        VideoUpdateResponseDto video = videoRepository.getVideo(videoId);
        if (video == null) {
            setValueForHeaderFooter(data, true, true, true, true);
            data.put("error", "Video not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        if (DTO.getVideoName().trim().isEmpty()) {
            setData(data, video.getCourseId(), video.getVideoName(), video.getFileVideo(), video.getLessonId());
            data.put("error", "Video name not empty");
            return new ResponseBase("manager_lesson/list", data);
        }

        if (!DTO.getFileVideo().trim().isEmpty()) {
            video.setFileVideo(DTO.getFileVideo().trim());
        }

        video.setVideoName(DTO.getVideoName().trim());
        videoRepository.updateVideo(video.getVideoName(), video.getFileVideo(), Instant.now(), videoId);
        return new ResponseBase(String.format("redirect:/ManagerLesson/%d?lessonId=%d&name=%s&video=%s", video.getCourseId(), video.getLessonId(), DTO.getFileVideo().trim(), DTO.getFileVideo().trim().isEmpty() ? video.getFileVideo() : DTO.getFileVideo().trim()), data);
    }

    @Override
    public ResponseBase delete(int videoId, int courseId) {
        Map<String, Object> data = new HashMap<>();

        if (!videoRepository.isVideoExist(videoId)) {
            setValueForHeaderFooter(data, true, true, true, true);
            data.put("error", "Video not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        videoRepository.deleteById(videoId);
        return new ResponseBase(String.format("redirect:/ManagerLesson/%d", courseId), data);
    }
}
