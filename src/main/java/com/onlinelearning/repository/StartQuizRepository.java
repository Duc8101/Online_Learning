package com.onlinelearning.repository;

import com.onlinelearning.model.entity.StartQuiz;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StartQuizRepository extends JpaRepository<StartQuiz, String> {

    @Transactional
    @Modifying
    @Query("delete from StartQuiz sq where sq.student.userId = :studentId")
    void deleteAllByStudentId(long studentId);

    @Query("select sq.startQuizId from StartQuiz sq where sq.question.questionId = :questionId and sq.student.userId = :studentId")
    String getStartQuizId(int questionId, long studentId);

    @Transactional
    @Modifying
    @Query("update StartQuiz sq set sq.answer = :answer where sq.startQuizId = :startQuizId")
    void updateAnswer(Integer answer, String startQuizId);

    @Query("select sq.answer from StartQuiz sq where sq.question.questionId = :questionId and sq.student.userId = :studentId")
    List<Integer> getAnswers(int questionId, long studentId, Pageable pageable);

    @Query("select sq.answer from StartQuiz sq where sq.question.quiz.quizId = :quizId and sq.student.userId = :studentId\n"
            + "order by sq.question.questionId")
    List<Integer> getChosenAnswers(int quizId, long studentId);

    @Transactional
    @Modifying
    @Query("delete from StartQuiz sq where sq.student.userId = :studentId and sq.question.quiz.quizId = :quizId")
    void deleteAllByStudentIdAndQuizId(long studentId, int quizId);
}
