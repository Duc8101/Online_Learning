package com.onlinelearning.service.impl;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.service.AboutService;
import com.onlinelearning.service.common.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AboutServiceImpl extends BaseService implements AboutService {

    @Override
    public ResponseBase about() {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);
        return new ResponseBase().withData(data).withViewName("about");
    }
}
