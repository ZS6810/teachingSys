package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.LearningProgress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LearningProgressService {
    LearningProgress save(LearningProgress learningProgress);
    Optional<LearningProgress> findById(Long id);
    List<LearningProgress> findByUserId(Long userId);
    List<LearningProgress> findByCourseId(Long courseId);
    Optional<LearningProgress> findByUserIdAndChapterId(Long userId, Long chapterId);
    Optional<LearningProgress> findByUserIdAndMaterialId(Long userId, Long materialId);
    Page<LearningProgress> findAll(Pageable pageable);
    void deleteById(Long id);
    LearningProgress update(LearningProgress learningProgress);
}

