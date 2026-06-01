package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.RegisterRequestDto;

public interface RegisterService {

    ResponseBase register();

    ResponseBase register(RegisterRequestDto DTO) throws Exception;
}
