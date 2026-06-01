package com.onlinelearning.mapper;

import com.onlinelearning.model.dto.response.LessonListForLearnCourseResponseDto;
import com.onlinelearning.model.dto.response.PdfListResponseDto;
import com.onlinelearning.model.dto.response.VideoListResponseDto;
import com.onlinelearning.model.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", imports = {VideoListResponseDto.class, PdfListResponseDto.class})
public interface LessonMapper {

    @Mapping(target = "quizExist", expression = "java(!lesson.getQuizzes().isEmpty())")
    @Mapping(target = "videos", expression = "java(lesson.getVideos().stream().map(v -> new VideoListResponseDto(v.getVideoId(), v.getVideoName(), v.getFileVideo(), v.getLesson().getLessonId())).toList())")
    @Mapping(target = "pdfs", expression = "java(lesson.getPdfs().stream().map(p -> new PdfListResponseDto(p.getPdfId(), p.getPdfName(), p.getFilePdf(), p.getLesson().getLessonId())).toList())")
    LessonListForLearnCourseResponseDto toLessonListForLearnCourseResponseDto(Lesson lesson);

    List<LessonListForLearnCourseResponseDto> toLessonListForLearnCourseResponseDTOs(List<Lesson> lessons);
}
