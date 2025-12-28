package com.teach.teachingsys.repository;

import com.teach.teachingsys.entity.AssignmentBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentBankRepository extends JpaRepository<AssignmentBank, Long> {
    @Query("SELECT ab FROM AssignmentBank ab WHERE ab.assignment.id = :assignmentId ORDER BY ab.questionOrder ASC")
    List<AssignmentBank> findByAssignmentId(@Param("assignmentId") Long assignmentId);
    
    @Query("SELECT ab FROM AssignmentBank ab WHERE ab.question.id = :questionId")
    List<AssignmentBank> findByQuestionId(@Param("questionId") Long questionId);
}

