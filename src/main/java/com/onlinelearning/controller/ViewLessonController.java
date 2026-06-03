package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.service.ViewLessonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ViewLesson")
@AllArgsConstructor
public class ViewLessonController {

    private final ViewLessonService viewLessonService;

    @GetMapping("/{courseId}")
    public ModelAndView viewLesson(@PathVariable int courseId, String video, String name, String pdf, Integer lessonId) {
        ResponseBase responseBase = viewLessonService.viewLesson(courseId, video, name, pdf, lessonId);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
