package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.CourseMaterial;
import com.teach.teachingsys.service.CourseMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-materials")
@RequiredArgsConstructor
public class CourseMaterialController {

    private final CourseMaterialService courseMaterialService;

    @PostMapping
    public ResponseEntity<CourseMaterial> create(@RequestBody CourseMaterial courseMaterial) {
        CourseMaterial saved = courseMaterialService.save(courseMaterial);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseMaterial> getById(@PathVariable Long id) {
        return courseMaterialService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/chapter/{chapterId}")
    public ResponseEntity<List<CourseMaterial>> getByChapterId(@PathVariable Long chapterId) {
        return ResponseEntity.ok(courseMaterialService.findByChapterId(chapterId));
    }

    @GetMapping
    public ResponseEntity<Page<CourseMaterial>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(courseMaterialService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseMaterial> update(@PathVariable Long id, @RequestBody CourseMaterial courseMaterial) {
        return courseMaterialService.findById(id)
                .map(existing -> {
                    courseMaterial.setId(id);
                    return ResponseEntity.ok(courseMaterialService.update(courseMaterial));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (courseMaterialService.findById(id).isPresent()) {
            courseMaterialService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

