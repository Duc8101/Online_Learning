package com.onlinelearning.model.dto.response;

import com.onlinelearning.model.enumeration.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDetailResponseDto {

    private String resultId;
    private int quizId;
    private long studentId;
    private String studentName;
    private double score;

    public String getStatus() {
        return this.score >= 5 ? ResultStatus.PASSED.getDisplayName() : ResultStatus.NOT_PASSED.getDisplayName();
    }
}
