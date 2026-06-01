package com.onlinelearning.configuration;

import com.onlinelearning.model.ResponseBase;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseBase handleGeneral(Exception ex, HttpServletResponse response) {
        Map<String, Object> data = new HashMap<>();
        data.put("error", ex.getMessage() + " " + ex);
        data.put("spinner", true);
        data.put("navbar", true);
        data.put("footer", true);
        data.put("script", true);
        data.put("code", response.getStatus());
        return new ResponseBase().withData(data).withViewName("shared/error");
    }
}
