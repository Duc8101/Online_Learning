package com.onlinelearning.service.impl;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.response.ResultDetailResponseDto;
import com.onlinelearning.repository.ResultRepository;
import com.onlinelearning.service.ResultService;
import com.onlinelearning.service.common.BaseService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultServiceImpl extends BaseService implements ResultService {

    final ResultRepository resultRepository;

    @Override
    public ResponseBase result(int quizId, long userId) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);
        List<ResultDetailResponseDto> results = resultRepository.getLatestResult(quizId, userId, PageRequest.of(0, 1));
        if (results.isEmpty()) {
            data.put("error", "Result not found or quiz might be deleted or not found course");
            return new ResponseBase().withData(data).withViewName("shared/error");
        }

        data.put("result", results.getFirst());
        return new ResponseBase("result", data);
    }
}
