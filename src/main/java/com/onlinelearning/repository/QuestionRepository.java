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

    @Query("select count (q.questionId) from Question q where q.quizId = :quizId")
    int getNumberQuestion(int quizId);

    @Query("""
            select new com.onlinelearning.model.dto.response.QuestionListForStartQuizResponseDto(q.questionId, q.questionName, q.quizId, q.answer1, q.answer2, q.answer3, q.answer4, null)\s
            from Question q
            where q.quizId = :quizId""")
    List<QuestionListForStartQuizResponseDto> getQuestionsForStartQuiz(int quizId, Pageable pageable);

    @Query("""
             select q.answerCorrect
             from Question q\s
             where q.quizId = :quizId and exists (
                         select 1 from StartQuiz sq where sq.questionId = q.questionId and sq.studentId = :studentId)
            \s""")
    List<Integer> getAnswersCorrect(int quizId, long studentId);

    @Query("""
            select new com.onlinelearning.model.dto.response.QuestionListForManagerQuestionResponseDto(q.questionId, q.questionName, q.quizId) from Question q
            where q.quizId = :quizId
            order by CASE WHEN q.updatedAt IS NULL THEN q.createdAt ELSE q.updatedAt END desc""")
    List<QuestionListForManagerQuestionResponseDto> getQuestionsForManagerQuestion(int quizId);

    @Query("""
            select new com.onlinelearning.model.dto.response.QuestionDetailResponseDto(q, c.creatorId)
            from Question q join Quiz qz on q.quizId = qz.quizId join Lesson l on qz.lessonId = l.lessonId join Course c on l.courseId = c.courseId
            where q.questionId = :questionId and c.deleted = false""")
    QuestionDetailResponseDto getQuestionDetail(int questionId);

    @Query("""
            select new com.onlinelearning.model.dto.response.QuestionForDeleteQuestionResponseDto(l.courseId, q.quizId)
            from Question q join Quiz qz on q.quizId = qz.quizId join Lesson l on qz.lessonId = l.lessonId
            where q.questionId = :questionId""")
    QuestionForDeleteQuestionResponseDto getQuestionInfoForDeleteQuestion(int questionId);
}
