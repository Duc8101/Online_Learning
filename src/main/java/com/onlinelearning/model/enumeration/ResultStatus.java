package com.onlinelearning.model.enumeration;

import lombok.Getter;

@Getter
public enum ResultStatus {

    PASSED("Passed"),
    NOT_PASSED("Not passed");

    private final String displayName;

    ResultStatus(String displayName) {
        this.displayName = displayName;
    }
}
