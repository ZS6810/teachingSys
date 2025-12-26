package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.AssignmentBank;
import com.teach.teachingsys.service.AssignmentBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignment-banks")
@RequiredArgsConstructor
public class AssignmentBankController {

    private final AssignmentBankService assignmentBankService;

    @PostMapping
    public ResponseEntity<AssignmentBank> create(@RequestBody AssignmentBank assignmentBank) {
        AssignmentBank saved = assignmentBankService.save(assignmentBank);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentBank> getById(@PathVariable Long id) {
        return assignmentBankService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<AssignmentBank>> getByAssignmentId(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(assignmentBankService.findByAssignmentId(assignmentId));
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<AssignmentBank>> getByQuestionId(@PathVariable Long questionId) {
        return ResponseEntity.ok(assignmentBankService.findByQuestionId(questionId));
    }

    @GetMapping
    public ResponseEntity<Page<AssignmentBank>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(assignmentBankService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentBank> update(@PathVariable Long id, @RequestBody AssignmentBank assignmentBank) {
        return assignmentBankService.findById(id)
                .map(existing -> {
                    assignmentBank.setId(id);
                    return ResponseEntity.ok(assignmentBankService.update(assignmentBank));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (assignmentBankService.findById(id).isPresent()) {
            assignmentBankService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

