package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.UserCourse;
import com.teach.teachingsys.service.UserCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-courses")
@RequiredArgsConstructor
public class UserCourseController {

    private final UserCourseService userCourseService;

    @PostMapping
    public ResponseEntity<UserCourse> create(@RequestBody UserCourse userCourse) {
        UserCourse saved = userCourseService.save(userCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserCourse> getById(@PathVariable Long id) {
        return userCourseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserCourse>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userCourseService.findByUserId(userId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<UserCourse>> getByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(userCourseService.findByCourseId(courseId));
    }

    @GetMapping("/user/{userId}/course/{courseId}")
    public ResponseEntity<UserCourse> getByUserIdAndCourseId(
            @PathVariable Long userId,
            @PathVariable Long courseId) {
        return userCourseService.findByUserIdAndCourseId(userId, courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<UserCourse>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(userCourseService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserCourse> update(@PathVariable Long id, @RequestBody UserCourse userCourse) {
        return userCourseService.findById(id)
                .map(existing -> {
                    userCourse.setId(id);
                    return ResponseEntity.ok(userCourseService.update(userCourse));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (userCourseService.findById(id).isPresent()) {
            userCourseService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

