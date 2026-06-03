package com.onlinelearning.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherCreateRequestDto {

    private String fullName;
    private String phone;
    private String email;
    private String gender;
    private String username;
}
