package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.ChangePasswordRequestDto;

public interface ChangePasswordService {

    ResponseBase changePassword();

    ResponseBase changePassword(ChangePasswordRequestDto DTO, long userId) throws Exception;
}
