package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.QuestionCreateRequestDto;
import com.onlinelearning.model.dto.request.QuestionUpdateRequestDto;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.service.ManagerQuestionService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ManagerQuestion")
@AllArgsConstructor
public class ManagerQuestionController {

    private final ManagerQuestionService managerQuestionService;

    @GetMapping("/{quizId}")
    public ModelAndView list(@PathVariable int quizId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerQuestionService.list(quizId, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping("/Create")
    public ModelAndView create(String quizId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerQuestionService.create(Integer.parseInt(quizId), user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @PostMapping("/Create")
    public ModelAndView create(QuestionCreateRequestDto DTO) {
        ResponseBase responseBase = managerQuestionService.create(DTO);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping("/Detail/{questionId}")
    public ModelAndView detail(@PathVariable int questionId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerQuestionService.detail(questionId, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping("/Update/{questionId}")
    public ModelAndView update(@PathVariable int questionId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerQuestionService.update(questionId, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @PostMapping("/Update/{questionId}")
    public ModelAndView update(@PathVariable int questionId, QuestionUpdateRequestDto DTO) {
        ResponseBase responseBase = managerQuestionService.update(questionId, DTO);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping("/Delete/{questionId}")
    public ModelAndView delete(@PathVariable String questionId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerQuestionService.delete(Integer.parseInt(questionId), user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
