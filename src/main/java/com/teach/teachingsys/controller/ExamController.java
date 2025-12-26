package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.Exam;
import com.teach.teachingsys.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public ResponseEntity<Exam> create(@RequestBody Exam exam) {
        Exam saved = examService.save(exam);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getById(@PathVariable Long id) {
        return examService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<Exam> getByAssignmentId(@PathVariable Long assignmentId) {
        return examService.findByAssignmentId(assignmentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Exam>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(examService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> update(@PathVariable Long id, @RequestBody Exam exam) {
        return examService.findById(id)
                .map(existing -> {
                    exam.setId(id);
                    return ResponseEntity.ok(examService.update(exam));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (examService.findById(id).isPresent()) {
            examService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

