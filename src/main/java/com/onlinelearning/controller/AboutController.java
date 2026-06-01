package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.service.AboutService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("About")
@AllArgsConstructor
public class AboutController {

    private final AboutService aboutService;

    @GetMapping("")
    public ModelAndView about() {
        ResponseBase responseBase = aboutService.about();
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
