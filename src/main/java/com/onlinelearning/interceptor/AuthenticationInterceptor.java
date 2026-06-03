package com.onlinelearning.interceptor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        // if not login
        if (request.getSession().getAttribute("user") == null) {
            request.setAttribute(RequestDispatcher.ERROR_MESSAGE, "Authentication Failed");
            request.getRequestDispatcher(String.format("/error/%d", HttpStatus.UNAUTHORIZED.value())).forward(request, response);
            return false;
        }
        return true;
    }
}
