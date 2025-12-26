package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.ExamBank;
import com.teach.teachingsys.service.ExamBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam-banks")
@RequiredArgsConstructor
public class ExamBankController {

    private final ExamBankService examBankService;

    @PostMapping
    public ResponseEntity<ExamBank> create(@RequestBody ExamBank examBank) {
        ExamBank saved = examBankService.save(examBank);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamBank> getById(@PathVariable Long id) {
        return examBankService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<ExamBank>> getByExamId(@PathVariable Long examId) {
        return ResponseEntity.ok(examBankService.findByExamId(examId));
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<ExamBank>> getByQuestionId(@PathVariable Long questionId) {
        return ResponseEntity.ok(examBankService.findByQuestionId(questionId));
    }

    @GetMapping
    public ResponseEntity<Page<ExamBank>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(examBankService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamBank> update(@PathVariable Long id, @RequestBody ExamBank examBank) {
        return examBankService.findById(id)
                .map(existing -> {
                    examBank.setId(id);
                    return ResponseEntity.ok(examBankService.update(examBank));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (examBankService.findById(id).isPresent()) {
            examBankService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

