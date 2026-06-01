package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface LoginService {

    ResponseBase login(HttpServletRequest request, HttpServletResponse response);

    ResponseBase login(String username, String password, HttpSession session, HttpServletResponse response) throws Exception;
}
