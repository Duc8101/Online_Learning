package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.ProfileRequestDto;
import com.onlinelearning.service.ProfileService;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Profile")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileController {

    final ProfileService profileService;

    @GetMapping("")
    public ModelAndView profile() {
        ResponseBase responseBase = profileService.profile();
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @PostMapping("")
    public ModelAndView profile(ProfileRequestDto DTO, HttpSession session) {
        ResponseBase responseBase = profileService.profile(DTO, session);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
