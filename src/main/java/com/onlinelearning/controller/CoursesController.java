package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.service.CoursesService;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Courses")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoursesController {

    final CoursesService coursesService;

    @GetMapping("")
    public ModelAndView list(Integer categoryId, Boolean orderBy, Integer page, HttpSession session) {
        ResponseBase responseBase = coursesService.list(categoryId, orderBy, page, session);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping({"/Detail/{courseId}"})
    public ModelAndView detail(@PathVariable int courseId, HttpSession session) {
        ResponseBase responseBase = coursesService.detail(courseId, session);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping({ "/EnrollCourse/{courseId}"})
    public ModelAndView enrollCourse(@PathVariable int courseId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = coursesService.enrollCourse(courseId, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping({ "/LearnCourse/{courseId}"})
    public ModelAndView learnCourse(@PathVariable int courseId, String video, String name, String pdf, Integer lessonId, Integer videoId, Integer pdfId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        ResponseBase responseBase = coursesService.learnCourse(courseId, video, name, pdf, lessonId, videoId, pdfId, user.getUserId());
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping("/Delete/{courseId}")
    public ModelAndView delete(@PathVariable int courseId) {
        ResponseBase responseBase = coursesService.delete(courseId);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
