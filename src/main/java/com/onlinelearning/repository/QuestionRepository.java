package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.QuestionListForStartQuizResponseDto;
import com.onlinelearning.model.entity.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query("select count (q.questionId) from Question q where q.quiz.quizId = :quizId")
    int getNumberQuestion(int quizId);

    @Query("select new com.onlinelearning.model.dto.response.QuestionListForStartQuizResponseDto(q.questionId, q.questionName"
            + ", q.quiz.quizId, q.answer1, q.answer2, q.answer3, q.answer4, null) from Question q\n"
            + "where q.quiz.quizId = :quizId")
    List<QuestionListForStartQuizResponseDto> getQuestionsForStartQuiz(int quizId, Pageable pageable);
}
