package com.onlinelearning.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoUpdateResponseDto {

    private int videoId;
    private String videoName;
    private String fileVideo;
    private int lessonId;
    private int courseId;
}
