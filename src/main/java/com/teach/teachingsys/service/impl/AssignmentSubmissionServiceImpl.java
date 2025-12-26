package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.AssignmentSubmission;
import com.teach.teachingsys.repository.AssignmentSubmissionRepository;
import com.teach.teachingsys.service.AssignmentSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AssignmentSubmissionServiceImpl implements AssignmentSubmissionService {

    private final AssignmentSubmissionRepository assignmentSubmissionRepository;

    @Override
    public AssignmentSubmission save(AssignmentSubmission assignmentSubmission) {
        return assignmentSubmissionRepository.save(assignmentSubmission);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AssignmentSubmission> findById(Long id) {
        return assignmentSubmissionRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignmentSubmission> findByAssignmentId(Long assignmentId) {
        return assignmentSubmissionRepository.findByAssignmentId(assignmentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignmentSubmission> findByUserId(Long userId) {
        return assignmentSubmissionRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AssignmentSubmission> findByAssignmentIdAndUserIdAndAttemptNumber(Long assignmentId, Long userId, Integer attemptNumber) {
        return assignmentSubmissionRepository.findByAssignmentIdAndUserIdAndAttemptNumber(assignmentId, userId, attemptNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssignmentSubmission> findAll(Pageable pageable) {
        return assignmentSubmissionRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        assignmentSubmissionRepository.deleteById(id);
    }

    @Override
    public AssignmentSubmission update(AssignmentSubmission assignmentSubmission) {
        return assignmentSubmissionRepository.save(assignmentSubmission);
    }
}

