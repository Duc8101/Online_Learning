package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.VideoCreateRequestDto;
import com.onlinelearning.model.dto.request.VideoUpdateRequestDto;

public interface ManagerVideoService {

    ResponseBase create(VideoCreateRequestDto DTO);

    ResponseBase update(int videoId, VideoUpdateRequestDto DTO);

    ResponseBase delete(int videoId, int courseId);
}
