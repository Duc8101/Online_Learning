package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.ChangePasswordRequestDto;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.service.ChangePasswordService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ChangePassword")
@AllArgsConstructor
public class ChangePasswordController {

    private final ChangePasswordService changePasswordService;

    @GetMapping("")
    public ModelAndView changePassword() {
        ResponseBase responseBase = changePasswordService.changePassword();
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @PostMapping("")
    public ModelAndView changePassword(ChangePasswordRequestDto DTO, HttpSession session) throws Exception {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = changePasswordService.changePassword(DTO, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
