package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.AssignmentBank;
import com.teach.teachingsys.repository.AssignmentBankRepository;
import com.teach.teachingsys.service.AssignmentBankService;
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
public class AssignmentBankServiceImpl implements AssignmentBankService {

    private final AssignmentBankRepository assignmentBankRepository;

    @Override
    public AssignmentBank save(AssignmentBank assignmentBank) {
        return assignmentBankRepository.save(assignmentBank);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AssignmentBank> findById(Long id) {
        return assignmentBankRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignmentBank> findByAssignmentId(Long assignmentId) {
        return assignmentBankRepository.findByAssignmentId(assignmentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignmentBank> findByQuestionId(Long questionId) {
        return assignmentBankRepository.findByQuestionId(questionId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssignmentBank> findAll(Pageable pageable) {
        return assignmentBankRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        assignmentBankRepository.deleteById(id);
    }

    @Override
    public AssignmentBank update(AssignmentBank assignmentBank) {
        return assignmentBankRepository.save(assignmentBank);
    }
}

