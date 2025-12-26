package com.teach.teachingsys.repository;

import com.teach.teachingsys.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query("SELECT e FROM Exam e WHERE e.assignment.id = :assignmentId")
    Optional<Exam> findByAssignmentId(@Param("assignmentId") Long assignmentId);
}

