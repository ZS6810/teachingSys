package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.LearningProgress;
import com.teach.teachingsys.repository.LearningProgressRepository;
import com.teach.teachingsys.service.LearningProgressService;
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
public class LearningProgressServiceImpl implements LearningProgressService {

    private final LearningProgressRepository learningProgressRepository;

    @Override
    public LearningProgress save(LearningProgress learningProgress) {
        return learningProgressRepository.save(learningProgress);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LearningProgress> findById(Long id) {
        return learningProgressRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LearningProgress> findByUserId(Long userId) {
        return learningProgressRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LearningProgress> findByCourseId(Long courseId) {
        return learningProgressRepository.findByCourseId(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LearningProgress> findByUserIdAndChapterId(Long userId, Long chapterId) {
        return learningProgressRepository.findByUserIdAndChapterId(userId, chapterId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LearningProgress> findByUserIdAndMaterialId(Long userId, Long materialId) {
        return learningProgressRepository.findByUserIdAndMaterialId(userId, materialId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LearningProgress> findAll(Pageable pageable) {
        return learningProgressRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        learningProgressRepository.deleteById(id);
    }

    @Override
    public LearningProgress update(LearningProgress learningProgress) {
        return learningProgressRepository.save(learningProgress);
    }
}

