package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.service.ForgotPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ForgotPassword")
@AllArgsConstructor
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @GetMapping("")
    public ModelAndView forgotPassword() {
        ResponseBase responseBase = forgotPasswordService.forgotPassword();
        return new ModelAndView(responseBase.getViewName(),  responseBase.getData());
    }

    @PostMapping("")
    public ModelAndView forgotPassword(String email) throws Exception {
        ResponseBase responseBase = forgotPasswordService.forgotPassword(email);
        return new ModelAndView(responseBase.getViewName(),  responseBase.getData());
    }
}
