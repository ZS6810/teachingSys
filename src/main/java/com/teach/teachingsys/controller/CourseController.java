package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.Course;
import com.teach.teachingsys.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        Course saved = courseService.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        return courseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Course>> getByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(courseService.findByTeacherId(teacherId));
    }

    @GetMapping
    public ResponseEntity<Page<Course>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(courseService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
        return courseService.findById(id)
                .map(existing -> {
                    course.setId(id);
                    return ResponseEntity.ok(courseService.update(course));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (courseService.findById(id).isPresent()) {
            courseService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

