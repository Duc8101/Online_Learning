package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.LessonCreateRequestDto;
import com.onlinelearning.model.dto.request.LessonUpdateRequestDto;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.service.ManagerLessonService;
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
@RequestMapping("/ManagerLesson")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerLessonController {

    final ManagerLessonService managerLessonService;

    @GetMapping("/{courseId}")
    public ModelAndView list(@PathVariable int courseId, String video, String name, String pdf, Integer lessonId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerLessonService.list(courseId, video, name, pdf, lessonId, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @PostMapping("/Create")
    public ModelAndView create(LessonCreateRequestDto DTO) {
        ResponseBase responseBase = managerLessonService.create(DTO);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @PostMapping("/Update/{lessonId}")
    public ModelAndView update(@PathVariable int lessonId, LessonUpdateRequestDto DTO) {
        ResponseBase responseBase = managerLessonService.update(lessonId, DTO);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping("/Delete/{lessonId}")
    public ModelAndView delete(@PathVariable int lessonId) {
        ResponseBase responseBase = managerLessonService.delete(lessonId);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}



