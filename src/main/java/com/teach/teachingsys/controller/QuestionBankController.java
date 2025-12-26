package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.QuestionBank;
import com.teach.teachingsys.service.QuestionBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/question-banks")
@RequiredArgsConstructor
public class QuestionBankController {

    private final QuestionBankService questionBankService;

    @PostMapping
    public ResponseEntity<QuestionBank> create(@RequestBody QuestionBank questionBank) {
        QuestionBank saved = questionBankService.save(questionBank);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionBank> getById(@PathVariable Long id) {
        return questionBankService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<QuestionBank>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(questionBankService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionBank> update(@PathVariable Long id, @RequestBody QuestionBank questionBank) {
        return questionBankService.findById(id)
                .map(existing -> {
                    questionBank.setId(id);
                    return ResponseEntity.ok(questionBankService.update(questionBank));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (questionBankService.findById(id).isPresent()) {
            questionBankService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

