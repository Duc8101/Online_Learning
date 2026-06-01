package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Logout")
@AllArgsConstructor
public class LogoutController {

    private final LogoutService logoutService;

    @GetMapping
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        ResponseBase responseBase = logoutService.logout(request, response);
        return new ModelAndView(responseBase.getViewName());
    }
}
