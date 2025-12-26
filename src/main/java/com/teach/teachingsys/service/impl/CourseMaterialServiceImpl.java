package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.CourseMaterial;
import com.teach.teachingsys.repository.CourseMaterialRepository;
import com.teach.teachingsys.service.CourseMaterialService;
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
public class CourseMaterialServiceImpl implements CourseMaterialService {

    private final CourseMaterialRepository courseMaterialRepository;

    @Override
    public CourseMaterial save(CourseMaterial courseMaterial) {
        return courseMaterialRepository.save(courseMaterial);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourseMaterial> findById(Long id) {
        return courseMaterialRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseMaterial> findByChapterId(Long chapterId) {
        return courseMaterialRepository.findByChapterId(chapterId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CourseMaterial> findAll(Pageable pageable) {
        return courseMaterialRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        courseMaterialRepository.deleteById(id);
    }

    @Override
    public CourseMaterial update(CourseMaterial courseMaterial) {
        return courseMaterialRepository.save(courseMaterial);
    }
}

