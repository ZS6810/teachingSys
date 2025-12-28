package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.QuestionBank;
import com.teach.teachingsys.repository.QuestionBankRepository;
import com.teach.teachingsys.service.QuestionBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(cacheNames = "questionBank", key = "#result.id")
    public QuestionBank save(QuestionBank questionBank) {
        return questionBankRepository.save(questionBank);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "questionBank", key = "#id", unless = "#result == null")
    public Optional<QuestionBank> findById(Long id) {
        return questionBankRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuestionBank> findAll(Pageable pageable) {
        return questionBankRepository.findAll(pageable);
    }

    @Override
    @CacheEvict(cacheNames = "questionBank", key = "#id")
    public void deleteById(Long id) {
        questionBankRepository.deleteById(id);
    }

    @Override
    @CacheEvict(cacheNames = "questionBank", key = "#result.id")
    public QuestionBank update(QuestionBank questionBank) {
        return questionBankRepository.save(questionBank);
    }
}

