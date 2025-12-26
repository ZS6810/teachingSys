package com.teach.teachingsys.repository;

import com.teach.teachingsys.entity.ChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChoiceQuestionRepository extends JpaRepository<ChoiceQuestion, Long> {
    @Query("SELECT cq FROM ChoiceQuestion cq WHERE cq.question.id = :questionId")
    Optional<ChoiceQuestion> findByQuestionId(@Param("questionId") Long questionId);
}

