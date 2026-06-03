package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.TeacherCreateRequestDto;
import com.onlinelearning.service.ManagerUserService;
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
@RequestMapping("/ManagerUser")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerUserController {

    final ManagerUserService managerUserService;

    @GetMapping("")
    public ModelAndView list(String name) {
        ResponseBase responseBase = managerUserService.list(name);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping("/Detail/{userId}")
    public ModelAndView detail(@PathVariable int userId) {
        ResponseBase responseBase = managerUserService.detail(userId);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping("/Create")
    public ModelAndView create() {
        ResponseBase responseBase = managerUserService.create();
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @PostMapping("/Create")
    public ModelAndView create(TeacherCreateRequestDto DTO) throws Exception {
        ResponseBase responseBase = managerUserService.create(DTO);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
