package com.onlinelearning.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileRequestDto {

    String fullName;
    String phone;
    String image;
    String address;
    String email;
    String gender;
}
