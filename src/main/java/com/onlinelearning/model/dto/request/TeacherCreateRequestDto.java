package com.onlinelearning.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherCreateRequestDto {

    String fullName;
    String phone;
    String email;
    String gender;
    String username;
}
