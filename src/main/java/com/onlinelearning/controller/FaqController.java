package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.service.FaqService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("FAQ")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FaqController {

    final FaqService faqService;

    @GetMapping("")
    public ModelAndView faq() {
        ResponseBase responseBase = faqService.faq();
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
