package com.onlinelearning.model;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBase {

    private String viewName;
    private Map<String, Object> data;

    public ResponseBase withData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public ResponseBase withViewName(String viewName) {
        this.viewName = viewName;
        return this;
    }
}
