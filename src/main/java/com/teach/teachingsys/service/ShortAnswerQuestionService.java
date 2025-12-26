package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.ShortAnswerQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ShortAnswerQuestionService {
    ShortAnswerQuestion save(ShortAnswerQuestion shortAnswerQuestion);
    Optional<ShortAnswerQuestion> findById(Long id);
    Optional<ShortAnswerQuestion> findByQuestionId(Long questionId);
    Page<ShortAnswerQuestion> findAll(Pageable pageable);
    void deleteById(Long id);
    ShortAnswerQuestion update(ShortAnswerQuestion shortAnswerQuestion);
}

