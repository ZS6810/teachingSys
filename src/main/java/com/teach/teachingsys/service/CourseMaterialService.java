package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.CourseMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CourseMaterialService {
    CourseMaterial save(CourseMaterial courseMaterial);
    Optional<CourseMaterial> findById(Long id);
    List<CourseMaterial> findByChapterId(Long chapterId);
    Page<CourseMaterial> findAll(Pageable pageable);
    void deleteById(Long id);
    CourseMaterial update(CourseMaterial courseMaterial);
}

