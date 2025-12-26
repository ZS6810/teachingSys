package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ExamService {
    Exam save(Exam exam);
    Optional<Exam> findById(Long id);
    Optional<Exam> findByAssignmentId(Long assignmentId);
    Page<Exam> findAll(Pageable pageable);
    void deleteById(Long id);
    Exam update(Exam exam);
}

