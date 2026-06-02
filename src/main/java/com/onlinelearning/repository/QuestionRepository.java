package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.QuestionDetailResponseDto;
import com.onlinelearning.model.dto.response.QuestionForDeleteQuestionResponseDto;
import com.onlinelearning.model.dto.response.QuestionListForManagerQuestionResponseDto;
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

    @Query("select q.answerCorrect from Question q where q.quiz.quizId = :quizId and exists (\n"
            + " select 1 from StartQuiz sq where sq.question = q and sq.student.userId = :studentId)")
    List<Integer> getAnswersCorrect(int quizId, long studentId);

    @Query("""
            select new com.onlinelearning.model.dto.response.QuestionListForManagerQuestionResponseDto(q.questionId, q.questionName, q.quiz.quizId) from Question q
            where q.quiz.quizId = :quizId
            order by CASE WHEN q.updatedAt IS NULL THEN q.createdAt ELSE q.updatedAt END desc""")
    List<QuestionListForManagerQuestionResponseDto> getQuestionsForManagerQuestion(int quizId);

    @Query("select new com.onlinelearning.model.dto.response.QuestionDetailResponseDto(q, q.quiz.lesson.course.creator.userId) from Question q join fetch q.quiz where q.questionId = :questionId and q.quiz.lesson.course.deleted = false")
    QuestionDetailResponseDto getQuestionDetail(int questionId);

    @Query("select new com.onlinelearning.model.dto.response.QuestionForDeleteQuestionResponseDto(q.quiz.lesson.course.courseId, q.quiz.quizId) from Question q where q.questionId = :questionId")
    QuestionForDeleteQuestionResponseDto getQuestionInfoForDeleteQuestion(int questionId);
}
