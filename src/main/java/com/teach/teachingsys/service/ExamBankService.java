package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.ExamBank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ExamBankService {
    ExamBank save(ExamBank examBank);
    Optional<ExamBank> findById(Long id);
    List<ExamBank> findByExamId(Long examId);
    List<ExamBank> findByQuestionId(Long questionId);
    Page<ExamBank> findAll(Pageable pageable);
    void deleteById(Long id);
    ExamBank update(ExamBank examBank);
}

