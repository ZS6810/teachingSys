package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.Assignment;
import com.teach.teachingsys.repository.AssignmentRepository;
import com.teach.teachingsys.service.AssignmentService;
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
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Override
    public Assignment save(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Assignment> findById(Long id) {
        return assignmentRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Assignment> findByCourseId(Long courseId) {
        return assignmentRepository.findByCourseId(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Assignment> findAll(Pageable pageable) {
        return assignmentRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        assignmentRepository.deleteById(id);
    }

    @Override
    public Assignment update(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }
}

