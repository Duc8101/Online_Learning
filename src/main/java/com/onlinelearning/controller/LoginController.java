package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Login")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginController {

    final LoginService loginService;

    @GetMapping("")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ResponseBase responseBase = loginService.login(request, response);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @PostMapping("")
    public ModelAndView login(String username, String password, HttpSession session, HttpServletResponse response) throws Exception {
        ResponseBase responseBase = loginService.login(username, password, session, response);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
