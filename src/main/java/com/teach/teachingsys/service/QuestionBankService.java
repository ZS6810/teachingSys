package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.QuestionBank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface QuestionBankService {
    QuestionBank save(QuestionBank questionBank);
    Optional<QuestionBank> findById(Long id);
    Page<QuestionBank> findAll(Pageable pageable);
    void deleteById(Long id);
    QuestionBank update(QuestionBank questionBank);
}

