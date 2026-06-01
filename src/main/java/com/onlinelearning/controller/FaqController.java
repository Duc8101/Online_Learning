package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.service.FaqService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("FAQ")
@AllArgsConstructor
public class FaqController {

    private final FaqService faqService;

    @GetMapping("")
    public ModelAndView faq() {
        ResponseBase responseBase = faqService.faq();
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
