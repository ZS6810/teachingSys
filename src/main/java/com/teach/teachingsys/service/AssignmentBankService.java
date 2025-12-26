package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.AssignmentBank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AssignmentBankService {
    AssignmentBank save(AssignmentBank assignmentBank);
    Optional<AssignmentBank> findById(Long id);
    List<AssignmentBank> findByAssignmentId(Long assignmentId);
    List<AssignmentBank> findByQuestionId(Long questionId);
    Page<AssignmentBank> findAll(Pageable pageable);
    void deleteById(Long id);
    AssignmentBank update(AssignmentBank assignmentBank);
}

