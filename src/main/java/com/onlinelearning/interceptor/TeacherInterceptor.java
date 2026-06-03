package com.onlinelearning.interceptor;

import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.model.enumeration.UserRole;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TeacherInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        UserProfileResponseDto user = (UserProfileResponseDto) request.getSession().getAttribute("user");
        // if not login
        if (user == null) {
            request.setAttribute(RequestDispatcher.ERROR_MESSAGE, "Authentication Failed");
            request.getRequestDispatcher(String.format("/error/%d", HttpStatus.UNAUTHORIZED.value())).forward(request, response);
            return false;
        }

        // if login not as admin
        if (user.getRoleId() != UserRole.TEACHER.getValue()) {
            request.setAttribute(RequestDispatcher.ERROR_MESSAGE, "You are not allowed to access");
            request.getRequestDispatcher(String.format("/error/%d", HttpStatus.FORBIDDEN.value())).forward(request, response);
            return false;
        }
        return true;
    }
}
