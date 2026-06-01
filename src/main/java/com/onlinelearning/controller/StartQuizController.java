package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.StartQuizRequestDto;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.service.StartQuizService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/StartQuiz")
@AllArgsConstructor
public class StartQuizController {

    private final StartQuizService startQuizService;

    @GetMapping("")
    public ModelAndView startQuiz(int quizId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = startQuizService.startQuiz(quizId, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @PostMapping("")
    public ModelAndView startQuiz(StartQuizRequestDto DTO, int minutes, int questionNo, int seconds, String button, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = startQuizService.startQuiz(DTO, minutes, questionNo, seconds, user.getUserId(), button);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
