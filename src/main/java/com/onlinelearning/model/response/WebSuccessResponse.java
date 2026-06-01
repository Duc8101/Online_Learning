package com.onlinelearning.model.response;

import org.springframework.http.HttpStatus;

public class WebSuccessResponse extends WebResponse {

    public WebSuccessResponse() {
        setCode(HttpStatus.OK.value());
    }
}
