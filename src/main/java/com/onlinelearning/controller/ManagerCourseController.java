package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.CourseCreateUpdateRequestDto;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.service.ManagerCourseService;
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
@RequestMapping("/ManagerCourse")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerCourseController {

    final ManagerCourseService managerCourseService;

    @GetMapping("")
    public ModelAndView list(Integer page, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerCourseService.list(page == null ? 1 : page, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping("/Create")
    public ModelAndView create() {
        ResponseBase responseBase = managerCourseService.create();
        return new ModelAndView("manager_course/create", responseBase.getData());
    }

    @PostMapping("/Create")
    public ModelAndView create(CourseCreateUpdateRequestDto DTO, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerCourseService.create(DTO, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping({"/Update/{courseId}"})
    public ModelAndView update(@PathVariable int courseId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerCourseService.update(courseId, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @PostMapping("/Update/{courseId}")
    public ModelAndView update(@PathVariable int courseId, CourseCreateUpdateRequestDto DTO, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerCourseService.update(courseId, DTO, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping({"/Delete/{courseId}"})
    public ModelAndView delete(@PathVariable int courseId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = managerCourseService.delete(courseId, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
