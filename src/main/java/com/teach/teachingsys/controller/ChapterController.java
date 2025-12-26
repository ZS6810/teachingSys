package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.Chapter;
import com.teach.teachingsys.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;

    @PostMapping
    public ResponseEntity<Chapter> create(@RequestBody Chapter chapter) {
        Chapter saved = chapterService.save(chapter);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chapter> getById(@PathVariable Long id) {
        return chapterService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Chapter>> getByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(chapterService.findByCourseId(courseId));
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<Chapter>> getByParentId(@PathVariable Long parentId) {
        return ResponseEntity.ok(chapterService.findByParentId(parentId));
    }

    @GetMapping
    public ResponseEntity<Page<Chapter>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(chapterService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chapter> update(@PathVariable Long id, @RequestBody Chapter chapter) {
        return chapterService.findById(id)
                .map(existing -> {
                    chapter.setId(id);
                    return ResponseEntity.ok(chapterService.update(chapter));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (chapterService.findById(id).isPresent()) {
            chapterService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

