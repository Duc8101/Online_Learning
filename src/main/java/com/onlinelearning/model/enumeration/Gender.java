package com.onlinelearning.model.enumeration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Gender {

    MALE("Male"),
    FEMALE( "Female"),
    OTHER( "Other");

    final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }
}
