package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.service.ResultService;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Result")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultController {

    final ResultService resultService;

    @GetMapping("")
    public ModelAndView result(String quizId,  HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = resultService.result(Integer.parseInt(quizId), user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
