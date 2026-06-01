package com.onlinelearning.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonListForLearnCourseResponseDto {

    private int lessonId;
    private String lessonName;
    private boolean quizExist;
    private List<VideoListResponseDto> videos = new ArrayList<>();
    private List<PdfListResponseDto> pdfs = new ArrayList<>();
}
