package com.onlinelearning.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewLessonResponseDto {

    int lessonId;
    String lessonName;
    List<VideoListResponseDto> videos = new ArrayList<>();
    List<PdfListResponseDto> pdfs = new ArrayList<>();
}
