package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.service.AboutService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("About")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutController {

    final AboutService aboutService;

    @GetMapping("")
    public ModelAndView about() {
        ResponseBase responseBase = aboutService.about();
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
