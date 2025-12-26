package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.Assignment;
import com.teach.teachingsys.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<Assignment> create(@RequestBody Assignment assignment) {
        Assignment saved = assignmentService.save(assignment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getById(@PathVariable Long id) {
        return assignmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Assignment>> getByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(assignmentService.findByCourseId(courseId));
    }

    @GetMapping
    public ResponseEntity<Page<Assignment>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(assignmentService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assignment> update(@PathVariable Long id, @RequestBody Assignment assignment) {
        return assignmentService.findById(id)
                .map(existing -> {
                    assignment.setId(id);
                    return ResponseEntity.ok(assignmentService.update(assignment));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (assignmentService.findById(id).isPresent()) {
            assignmentService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

