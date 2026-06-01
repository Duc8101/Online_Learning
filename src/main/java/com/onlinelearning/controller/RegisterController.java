package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.RegisterRequestDto;
import com.onlinelearning.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Register")
@AllArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @GetMapping("")
    public ModelAndView register() {
        ResponseBase responseBase = registerService.register();
        return new ModelAndView(responseBase.getViewName(),  responseBase.getData());
    }

    @PostMapping("")
    public ModelAndView register(RegisterRequestDto DTO) throws Exception {
        ResponseBase responseBase = registerService.register(DTO);
        return new ModelAndView(responseBase.getViewName(),  responseBase.getData());
    }
}
