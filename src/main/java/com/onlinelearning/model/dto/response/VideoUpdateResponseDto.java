package com.onlinelearning.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoUpdateResponseDto {

    int videoId;
    String videoName;
    String fileVideo;
    int lessonId;
    int courseId;
}
