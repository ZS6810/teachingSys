package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.ShortAnswerQuestion;
import com.teach.teachingsys.service.ShortAnswerQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/short-answer-questions")
@RequiredArgsConstructor
public class ShortAnswerQuestionController {

    private final ShortAnswerQuestionService shortAnswerQuestionService;

    @PostMapping
    public ResponseEntity<ShortAnswerQuestion> create(@RequestBody ShortAnswerQuestion shortAnswerQuestion) {
        ShortAnswerQuestion saved = shortAnswerQuestionService.save(shortAnswerQuestion);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShortAnswerQuestion> getById(@PathVariable Long id) {
        return shortAnswerQuestionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<ShortAnswerQuestion> getByQuestionId(@PathVariable Long questionId) {
        return shortAnswerQuestionService.findByQuestionId(questionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<ShortAnswerQuestion>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(shortAnswerQuestionService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShortAnswerQuestion> update(@PathVariable Long id, @RequestBody ShortAnswerQuestion shortAnswerQuestion) {
        return shortAnswerQuestionService.findById(id)
                .map(existing -> {
                    shortAnswerQuestion.setId(id);
                    return ResponseEntity.ok(shortAnswerQuestionService.update(shortAnswerQuestion));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (shortAnswerQuestionService.findById(id).isPresent()) {
            shortAnswerQuestionService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

