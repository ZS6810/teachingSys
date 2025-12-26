package com.teach.teachingsys.repository;

import com.teach.teachingsys.entity.ShortAnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortAnswerQuestionRepository extends JpaRepository<ShortAnswerQuestion, Long> {
    @Query("SELECT saq FROM ShortAnswerQuestion saq WHERE saq.question.id = :questionId")
    Optional<ShortAnswerQuestion> findByQuestionId(@Param("questionId") Long questionId);
}

