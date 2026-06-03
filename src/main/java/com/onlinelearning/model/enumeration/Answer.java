package com.onlinelearning.model.enumeration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Answer {

    ANSWER1(1),
    ANSWER2(2),
    ANSWER3(3),
    ANSWER4(4);

    final int value;

    Answer(int value) {
        this.value = value;
    }
}
