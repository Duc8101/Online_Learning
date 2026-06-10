package com.onlinelearning.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonListForLearnCourseResponseDto {

    public LessonListForLearnCourseResponseDto(int lessonId, String lessonName, boolean quizExist) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.quizExist = quizExist;
    }

    int lessonId;
    String lessonName;
    boolean quizExist;
    List<VideoListResponseDto> videos = new ArrayList<>();
    List<PdfListResponseDto> pdfs = new ArrayList<>();
}
