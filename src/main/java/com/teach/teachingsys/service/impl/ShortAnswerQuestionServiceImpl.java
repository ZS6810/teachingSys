package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.ShortAnswerQuestion;
import com.teach.teachingsys.repository.ShortAnswerQuestionRepository;
import com.teach.teachingsys.service.ShortAnswerQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShortAnswerQuestionServiceImpl implements ShortAnswerQuestionService {

    private final ShortAnswerQuestionRepository shortAnswerQuestionRepository;

    @Override
    public ShortAnswerQuestion save(ShortAnswerQuestion shortAnswerQuestion) {
        return shortAnswerQuestionRepository.save(shortAnswerQuestion);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ShortAnswerQuestion> findById(Long id) {
        return shortAnswerQuestionRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ShortAnswerQuestion> findByQuestionId(Long questionId) {
        return shortAnswerQuestionRepository.findByQuestionId(questionId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ShortAnswerQuestion> findAll(Pageable pageable) {
        return shortAnswerQuestionRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        shortAnswerQuestionRepository.deleteById(id);
    }

    @Override
    public ShortAnswerQuestion update(ShortAnswerQuestion shortAnswerQuestion) {
        return shortAnswerQuestionRepository.save(shortAnswerQuestion);
    }
}

