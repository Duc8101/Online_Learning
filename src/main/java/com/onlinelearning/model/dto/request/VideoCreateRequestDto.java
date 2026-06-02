package com.onlinelearning.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoCreateRequestDto {

    private String videoName;
    private String fileVideo;
    private int lessonId;
}
