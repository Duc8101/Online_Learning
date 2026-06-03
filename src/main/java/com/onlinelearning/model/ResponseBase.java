package com.onlinelearning.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseBase {

    String viewName;
    Map<String, Object> data;

    public ResponseBase withData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public ResponseBase withViewName(String viewName) {
        this.viewName = viewName;
        return this;
    }
}
