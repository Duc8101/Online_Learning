package com.onlinelearning.service.impl;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.service.LogoutService;
import com.onlinelearning.service.common.BaseService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class LogoutServiceImpl extends BaseService implements LogoutService {

    @Override
    public ResponseBase logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                break;
            }
        }
        return new ResponseBase("redirect:/Home", null);
    }
}
