package com.onlinelearning.model.response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class WebResponse {

    private int code;
    private String viewName;
    private Map<String, Object> data;

    public WebResponse withData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public WebResponse withViewName(String viewName) {
        this.viewName = viewName;
        return this;
    }
}
