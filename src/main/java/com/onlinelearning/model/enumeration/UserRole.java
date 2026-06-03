package com.onlinelearning.model.enumeration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum UserRole {

    ADMIN(1),
    TEACHER(2),
    STUDENT(3);

    final int value;

    UserRole(int value) {
        this.value = value;
    }
}
