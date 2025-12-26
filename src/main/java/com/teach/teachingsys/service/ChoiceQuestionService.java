package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.ChoiceQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ChoiceQuestionService {
    ChoiceQuestion save(ChoiceQuestion choiceQuestion);
    Optional<ChoiceQuestion> findById(Long id);
    Optional<ChoiceQuestion> findByQuestionId(Long questionId);
    Page<ChoiceQuestion> findAll(Pageable pageable);
    void deleteById(Long id);
    ChoiceQuestion update(ChoiceQuestion choiceQuestion);
}

