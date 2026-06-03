package com.onlinelearning.model.enumeration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ResultStatus {

    PASSED("Passed"),
    NOT_PASSED("Not passed");

    final String displayName;

    ResultStatus(String displayName) {
        this.displayName = displayName;
    }
}
