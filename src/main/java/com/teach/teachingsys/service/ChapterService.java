package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ChapterService {
    Chapter save(Chapter chapter);
    Optional<Chapter> findById(Long id);
    List<Chapter> findByCourseId(Long courseId);
    List<Chapter> findByParentId(Long parentId);
    Page<Chapter> findAll(Pageable pageable);
    void deleteById(Long id);
    Chapter update(Chapter chapter);
}

