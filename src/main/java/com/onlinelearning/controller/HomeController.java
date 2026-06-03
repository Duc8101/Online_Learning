package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.service.HomeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("Home")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeController {

    final HomeService homeService;

    @GetMapping("")
    public ModelAndView home() {
        ResponseBase responseBase = homeService.home();
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
