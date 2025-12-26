package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.Chapter;
import com.teach.teachingsys.repository.ChapterRepository;
import com.teach.teachingsys.service.ChapterService;
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
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;

    @Override
    public Chapter save(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Chapter> findById(Long id) {
        return chapterRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chapter> findByCourseId(Long courseId) {
        return chapterRepository.findByCourseId(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chapter> findByParentId(Long parentId) {
        return chapterRepository.findByParentId(parentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Chapter> findAll(Pageable pageable) {
        return chapterRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        chapterRepository.deleteById(id);
    }

    @Override
    public Chapter update(Chapter chapter) {
        return chapterRepository.save(chapter);
    }
}

