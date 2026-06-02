package com.onlinelearning.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonCreateRequestDto {

    private String lessonName;
    private int courseId;
}
