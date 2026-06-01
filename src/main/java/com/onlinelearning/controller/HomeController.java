package com.onlinelearning.controller;

import com.onlinelearning.model.WebResponse;
import com.onlinelearning.service.HomeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("Home")
@AllArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("")
    public ModelAndView home() {
        WebResponse webResponse = homeService.home();
        return new ModelAndView(webResponse.getViewName(), webResponse.getData());
    }
}
