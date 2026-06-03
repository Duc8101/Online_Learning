package com.onlinelearning.model.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequestDto {

    String fullName;
    String phone;
    String email;
    String address;
    String gender;
    String username;
}
