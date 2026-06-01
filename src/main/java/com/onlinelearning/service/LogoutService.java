package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LogoutService {

    ResponseBase logout(HttpServletRequest request, HttpServletResponse response);
}
