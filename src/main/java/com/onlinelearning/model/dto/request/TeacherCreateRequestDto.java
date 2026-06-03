package com.onlinelearning.model.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherCreateRequestDto {

    String fullName;
    String phone;
    String email;
    String gender;
    String username;
}
