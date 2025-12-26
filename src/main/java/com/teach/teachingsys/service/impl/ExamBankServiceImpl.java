package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.ExamBank;
import com.teach.teachingsys.repository.ExamBankRepository;
import com.teach.teachingsys.service.ExamBankService;
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
public class ExamBankServiceImpl implements ExamBankService {

    private final ExamBankRepository examBankRepository;

    @Override
    public ExamBank save(ExamBank examBank) {
        return examBankRepository.save(examBank);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExamBank> findById(Long id) {
        return examBankRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExamBank> findByExamId(Long examId) {
        return examBankRepository.findByExamId(examId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExamBank> findByQuestionId(Long questionId) {
        return examBankRepository.findByQuestionId(questionId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ExamBank> findAll(Pageable pageable) {
        return examBankRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        examBankRepository.deleteById(id);
    }

    @Override
    public ExamBank update(ExamBank examBank) {
        return examBankRepository.save(examBank);
    }
}

