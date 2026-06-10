package com.onlinelearning.model.dto.response;

import com.onlinelearning.model.enumeration.ResultStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultDetailResponseDto {

    String resultId;
    int quizId;
    long studentId;
    String studentName;
    double score;

    public String getStatus() {
        return this.score >= 5 ? ResultStatus.PASSED.getDisplayName() : ResultStatus.NOT_PASSED.getDisplayName();
    }
}
