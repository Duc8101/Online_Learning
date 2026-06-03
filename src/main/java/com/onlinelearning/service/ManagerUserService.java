package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.TeacherCreateRequestDto;

public interface ManagerUserService {

    ResponseBase list(String name);

    ResponseBase detail(long userId);

    ResponseBase create();

    ResponseBase create(TeacherCreateRequestDto DTO) throws Exception;
}
