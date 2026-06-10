package com.onlinelearning.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoCreateRequestDto {

    String videoName;
    String fileVideo;
    int lessonId;
}
