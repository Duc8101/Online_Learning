package com.onlinelearning.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequestDto {

    String fullName;
    String phone;
    String email;
    String address;
    String gender;
    String username;
}
