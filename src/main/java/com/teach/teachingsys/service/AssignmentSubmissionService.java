package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.AssignmentSubmission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AssignmentSubmissionService {
    AssignmentSubmission save(AssignmentSubmission assignmentSubmission);
    Optional<AssignmentSubmission> findById(Long id);
    List<AssignmentSubmission> findByAssignmentId(Long assignmentId);
    List<AssignmentSubmission> findByUserId(Long userId);
    Optional<AssignmentSubmission> findByAssignmentIdAndUserIdAndAttemptNumber(Long assignmentId, Long userId, Integer attemptNumber);
    Page<AssignmentSubmission> findAll(Pageable pageable);
    void deleteById(Long id);
    AssignmentSubmission update(AssignmentSubmission assignmentSubmission);
}

