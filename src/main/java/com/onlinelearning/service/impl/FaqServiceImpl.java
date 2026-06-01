package com.onlinelearning.service.impl;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.service.FaqService;
import com.onlinelearning.service.common.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FaqServiceImpl extends BaseService implements FaqService {

    @Override
    public ResponseBase faq() {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, false, true, true, false);
        return new ResponseBase().withData(data).withViewName("faq");
    }
}
