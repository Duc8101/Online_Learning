package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.LessonCreateRequestDto;
import com.onlinelearning.model.dto.request.LessonUpdateRequestDto;

public interface ManagerLessonService {

    ResponseBase list(int courseId, String fileVideo, String name, String filePdf, Integer lessonId, long userId);

    ResponseBase create(LessonCreateRequestDto DTO);

    ResponseBase update(int lessonId, LessonUpdateRequestDto DTO);

    ResponseBase delete(int lessonId);
}
