package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.ProfileRequestDto;
import jakarta.servlet.http.HttpSession;

public interface ProfileService {

    ResponseBase profile();

    ResponseBase profile(ProfileRequestDto DTO, HttpSession session);
}
