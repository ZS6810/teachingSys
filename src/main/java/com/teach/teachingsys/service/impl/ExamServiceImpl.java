package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.Exam;
import com.teach.teachingsys.repository.ExamRepository;
import com.teach.teachingsys.service.ExamService;
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
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    @Override
    @CacheEvict(cacheNames = "exam", key = "#result.id")
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "exam", key = "#id", unless = "#result == null")
    public Optional<Exam> findById(Long id) {
        return examRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Exam> findByAssignmentId(Long assignmentId) {
        return examRepository.findByAssignmentId(assignmentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Exam> findAll(Pageable pageable) {
        return examRepository.findAll(pageable);
    }

    @Override
    @CacheEvict(cacheNames = "exam", key = "#id")
    public void deleteById(Long id) {
        examRepository.deleteById(id);
    }

    @Override
    @CacheEvict(cacheNames = "exam", key = "#result.id")
    public Exam update(Exam exam) {
        return examRepository.save(exam);
    }
}

