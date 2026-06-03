package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.QuizCreateRequestDto;
import com.onlinelearning.model.dto.request.QuizUpdateRequestDto;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.service.ManagerQuizService;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ManagerQuiz")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerQuizController {

    final ManagerQuizService managerQuizService;

    @GetMapping("/{lessonId}")
    public ModelAndView list(@PathVariable int lessonId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerQuizService.list(lessonId, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping("/Create")
    public ModelAndView create(int lessonId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerQuizService.create(lessonId, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @PostMapping("/Create")
    public ModelAndView create(QuizCreateRequestDto DTO) {
        ResponseBase responseBase = managerQuizService.create(DTO);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping("/Update/{quizId}")
    public ModelAndView update(@PathVariable int quizId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerQuizService.update(quizId, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @PostMapping("/Update/{quizId}")
    public ModelAndView update(@PathVariable int quizId, QuizUpdateRequestDto DTO) {
        ResponseBase responseBase = managerQuizService.update(quizId, DTO);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping("/Delete/{quizId}")
    public ModelAndView delete(@PathVariable int quizId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerQuizService.delete(quizId, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
