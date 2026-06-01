package com.onlinelearning.model.enumeration;

import lombok.Getter;

@Getter
public enum Answer {

    ANSWER1(1),
    ANSWER2(2),
    ANSWER3(3),
    ANSWER4(4);

    private final int value;

    Answer(int value) {
        this.value = value;
    }
}
