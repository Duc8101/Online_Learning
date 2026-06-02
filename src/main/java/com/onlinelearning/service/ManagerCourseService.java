package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.CourseCreateUpdateRequestDto;

public interface ManagerCourseService {

    ResponseBase list(int page, long userId);

    ResponseBase create();

    ResponseBase create(CourseCreateUpdateRequestDto DTO, long userId);

    ResponseBase update(int courseId, long userId);

    ResponseBase update(int courseId, CourseCreateUpdateRequestDto DTO, long userId);

    ResponseBase delete(int courseId, long userId);
}
