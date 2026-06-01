package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.ResultDetailResponseDto;
import com.onlinelearning.model.entity.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, String> {

    @Query("select new com.onlinelearning.model.dto.response.ResultDetailResponseDto(r.resultId, r.quiz.quizId, r.student.userId"
            + ", r.student.userAccount.username, r.score) from Result r\n"
            + "where r.quiz.quizId = :quizId and r.student.userId = :studentId order by r.submittedAt desc")
    List<ResultDetailResponseDto> getLatestResult(int quizId, long studentId, Pageable pageable);
}
