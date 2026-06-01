package com.onlinelearning.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {

    private String fullName;
    private String phone;
    private String email;
    private String address;
    private String gender;
    private String username;
}
