package com.onlinelearning.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequestDto {

    private String fullName;
    private String phone;
    private String image;
    private String address;
    private String email;
    private String gender;
}
