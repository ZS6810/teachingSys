package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.AssignmentSubmission;
import com.teach.teachingsys.service.AssignmentSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignment-submissions")
@RequiredArgsConstructor
public class AssignmentSubmissionController {

    private final AssignmentSubmissionService assignmentSubmissionService;

    @PostMapping
    public ResponseEntity<AssignmentSubmission> create(@RequestBody AssignmentSubmission assignmentSubmission) {
        AssignmentSubmission saved = assignmentSubmissionService.save(assignmentSubmission);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentSubmission> getById(@PathVariable Long id) {
        return assignmentSubmissionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<AssignmentSubmission>> getByAssignmentId(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(assignmentSubmissionService.findByAssignmentId(assignmentId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AssignmentSubmission>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(assignmentSubmissionService.findByUserId(userId));
    }

    @GetMapping("/assignment/{assignmentId}/user/{userId}/attempt/{attemptNumber}")
    public ResponseEntity<AssignmentSubmission> getByAssignmentAndUserAndAttempt(
            @PathVariable Long assignmentId,
            @PathVariable Long userId,
            @PathVariable Integer attemptNumber) {
        return assignmentSubmissionService.findByAssignmentIdAndUserIdAndAttemptNumber(assignmentId, userId, attemptNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<AssignmentSubmission>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(assignmentSubmissionService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentSubmission> update(@PathVariable Long id, @RequestBody AssignmentSubmission assignmentSubmission) {
        return assignmentSubmissionService.findById(id)
                .map(existing -> {
                    assignmentSubmission.setId(id);
                    return ResponseEntity.ok(assignmentSubmissionService.update(assignmentSubmission));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (assignmentSubmissionService.findById(id).isPresent()) {
            assignmentSubmissionService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

