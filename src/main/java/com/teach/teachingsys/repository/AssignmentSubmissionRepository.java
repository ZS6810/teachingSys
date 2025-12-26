package com.teach.teachingsys.repository;

import com.teach.teachingsys.entity.AssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Long> {
    @Query("SELECT asub FROM AssignmentSubmission asub WHERE asub.assignment.id = :assignmentId")
    List<AssignmentSubmission> findByAssignmentId(@Param("assignmentId") Long assignmentId);
    
    @Query("SELECT asub FROM AssignmentSubmission asub WHERE asub.student.id = :userId")
    List<AssignmentSubmission> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT asub FROM AssignmentSubmission asub WHERE asub.assignment.id = :assignmentId AND asub.student.id = :userId AND asub.attemptNumber = :attemptNumber")
    Optional<AssignmentSubmission> findByAssignmentIdAndUserIdAndAttemptNumber(
            @Param("assignmentId") Long assignmentId, 
            @Param("userId") Long userId, 
            @Param("attemptNumber") Integer attemptNumber);
}

