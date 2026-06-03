package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.service.ViewLessonService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ViewLesson")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewLessonController {

    final ViewLessonService viewLessonService;

    @GetMapping("/{courseId}")
    public ModelAndView viewLesson(@PathVariable int courseId, String video, String name, String pdf, Integer lessonId) {
        ResponseBase responseBase = viewLessonService.viewLesson(courseId, video, name, pdf, lessonId);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
