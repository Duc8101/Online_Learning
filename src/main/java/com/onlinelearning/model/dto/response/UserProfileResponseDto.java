package com.onlinelearning.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileResponseDto {

    long userId;
    String fullName;
    String phone;
    String image;
    String address;
    String email;
    String gender;
    String username;
    int roleId;
}
