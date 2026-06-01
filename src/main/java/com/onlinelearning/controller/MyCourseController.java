package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.service.MyCourseService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/MyCourse")
@AllArgsConstructor
public class MyCourseController {

    private final MyCourseService myCourseService;

    @GetMapping("")
    public ModelAndView myCourse(Integer page, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = myCourseService.myCourse(page, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
