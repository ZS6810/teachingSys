package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.QuestionBank;
import com.teach.teachingsys.repository.QuestionBankRepository;
import com.teach.teachingsys.service.QuestionBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionBankServiceImpl implements QuestionBankService {

    private final QuestionBankRepository questionBankRepository;

    @Override
    public QuestionBank save(QuestionBank questionBank) {
        return questionBankRepository.save(questionBank);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionBank> findById(Long id) {
        return questionBankRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuestionBank> findAll(Pageable pageable) {
        return questionBankRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        questionBankRepository.deleteById(id);
    }

    @Override
    public QuestionBank update(QuestionBank questionBank) {
        return questionBankRepository.save(questionBank);
    }
}

