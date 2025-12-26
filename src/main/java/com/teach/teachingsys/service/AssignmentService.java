package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AssignmentService {
    Assignment save(Assignment assignment);
    Optional<Assignment> findById(Long id);
    List<Assignment> findByCourseId(Long courseId);
    Page<Assignment> findAll(Pageable pageable);
    void deleteById(Long id);
    Assignment update(Assignment assignment);
}

