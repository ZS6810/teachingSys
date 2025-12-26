package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.LearningProgress;
import com.teach.teachingsys.service.LearningProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/learning-progresses")
@RequiredArgsConstructor
public class LearningProgressController {

    private final LearningProgressService learningProgressService;

    @PostMapping
    public ResponseEntity<LearningProgress> create(@RequestBody LearningProgress learningProgress) {
        LearningProgress saved = learningProgressService.save(learningProgress);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearningProgress> getById(@PathVariable Long id) {
        return learningProgressService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LearningProgress>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(learningProgressService.findByUserId(userId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<LearningProgress>> getByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(learningProgressService.findByCourseId(courseId));
    }

    @GetMapping("/user/{userId}/chapter/{chapterId}")
    public ResponseEntity<LearningProgress> getByUserIdAndChapterId(
            @PathVariable Long userId,
            @PathVariable Long chapterId) {
        return learningProgressService.findByUserIdAndChapterId(userId, chapterId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}/material/{materialId}")
    public ResponseEntity<LearningProgress> getByUserIdAndMaterialId(
            @PathVariable Long userId,
            @PathVariable Long materialId) {
        return learningProgressService.findByUserIdAndMaterialId(userId, materialId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<LearningProgress>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(learningProgressService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearningProgress> update(@PathVariable Long id, @RequestBody LearningProgress learningProgress) {
        return learningProgressService.findById(id)
                .map(existing -> {
                    learningProgress.setId(id);
                    return ResponseEntity.ok(learningProgressService.update(learningProgress));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (learningProgressService.findById(id).isPresent()) {
            learningProgressService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

