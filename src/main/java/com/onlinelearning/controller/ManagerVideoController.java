package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.VideoCreateRequestDto;
import com.onlinelearning.model.dto.request.VideoUpdateRequestDto;
import com.onlinelearning.service.ManagerVideoService;
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
@RequestMapping("/ManagerVideo")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerVideoController {

    final ManagerVideoService managerVideoService;

    @PostMapping("/Create")
    public ModelAndView create(VideoCreateRequestDto DTO) {
        ResponseBase responseBase = managerVideoService.create(DTO);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @PostMapping("/Update/{videoId}")
    public ModelAndView update(@PathVariable int videoId, VideoUpdateRequestDto DTO) {
        ResponseBase responseBase = managerVideoService.update(videoId, DTO);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping("/Delete/{videoId}")
    public ModelAndView delete(@PathVariable int videoId, int courseId) {
        ResponseBase responseBase = managerVideoService.delete(videoId, courseId);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
