package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;

public interface ForgotPasswordService {

    ResponseBase forgotPassword();

    ResponseBase forgotPassword(String email) throws Exception;
}
