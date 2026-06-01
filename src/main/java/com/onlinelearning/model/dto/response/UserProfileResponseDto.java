package com.onlinelearning.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponseDto {

    private long userId;
    private String fullName;
    private String phone;
    private String image;
    private String address;
    private String email;
    private String gender;
    private String username;
    private int roleId;
}
