package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.ChoiceQuestion;
import com.teach.teachingsys.repository.ChoiceQuestionRepository;
import com.teach.teachingsys.service.ChoiceQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChoiceQuestionServiceImpl implements ChoiceQuestionService {

    private final ChoiceQuestionRepository choiceQuestionRepository;

    @Override
    public ChoiceQuestion save(ChoiceQuestion choiceQuestion) {
        return choiceQuestionRepository.save(choiceQuestion);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChoiceQuestion> findById(Long id) {
        return choiceQuestionRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChoiceQuestion> findByQuestionId(Long questionId) {
        return choiceQuestionRepository.findByQuestionId(questionId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChoiceQuestion> findAll(Pageable pageable) {
        return choiceQuestionRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        choiceQuestionRepository.deleteById(id);
    }

    @Override
    public ChoiceQuestion update(ChoiceQuestion choiceQuestion) {
        return choiceQuestionRepository.save(choiceQuestion);
    }
}

