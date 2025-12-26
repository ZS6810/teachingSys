package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.ChoiceQuestion;
import com.teach.teachingsys.service.ChoiceQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/choice-questions")
@RequiredArgsConstructor
public class ChoiceQuestionController {

    private final ChoiceQuestionService choiceQuestionService;

    @PostMapping
    public ResponseEntity<ChoiceQuestion> create(@RequestBody ChoiceQuestion choiceQuestion) {
        ChoiceQuestion saved = choiceQuestionService.save(choiceQuestion);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChoiceQuestion> getById(@PathVariable Long id) {
        return choiceQuestionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<ChoiceQuestion> getByQuestionId(@PathVariable Long questionId) {
        return choiceQuestionService.findByQuestionId(questionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<ChoiceQuestion>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(choiceQuestionService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChoiceQuestion> update(@PathVariable Long id, @RequestBody ChoiceQuestion choiceQuestion) {
        return choiceQuestionService.findById(id)
                .map(existing -> {
                    choiceQuestion.setId(id);
                    return ResponseEntity.ok(choiceQuestionService.update(choiceQuestion));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (choiceQuestionService.findById(id).isPresent()) {
            choiceQuestionService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

