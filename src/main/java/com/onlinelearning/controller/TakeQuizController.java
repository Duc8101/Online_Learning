package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.service.TakeQuizService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/TakeQuiz")
@AllArgsConstructor
public class TakeQuizController {

    private final TakeQuizService takeQuizService;

    @GetMapping("")
    public ModelAndView takeQuiz(String lessonId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = takeQuizService.takeQuiz(Integer.parseInt(lessonId), user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
