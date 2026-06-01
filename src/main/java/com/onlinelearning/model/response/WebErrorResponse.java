package com.onlinelearning.model.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WebErrorResponse extends WebResponse {

    public WebErrorResponse(int code) {
        setCode(code);
    }
}
