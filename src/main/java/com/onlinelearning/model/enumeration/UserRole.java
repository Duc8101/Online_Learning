package com.onlinelearning.model.enumeration;

import lombok.Getter;

@Getter
public enum UserRole {

    ADMIN(1),
    TEACHER(2),
    STUDENT(3);

    private final int value;

    UserRole(int value) {
        this.value = value;
    }
}
