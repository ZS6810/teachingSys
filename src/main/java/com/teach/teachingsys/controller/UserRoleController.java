package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.UserRole;
import com.teach.teachingsys.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService userRoleService;

    @PostMapping
    public ResponseEntity<UserRole> create(@RequestBody UserRole userRole) {
        UserRole saved = userRoleService.save(userRole);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRole> getById(@PathVariable Long id) {
        return userRoleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRole>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userRoleService.findByUserId(userId));
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<UserRole>> getByRoleId(@PathVariable Long roleId) {
        return ResponseEntity.ok(userRoleService.findByRoleId(roleId));
    }

    @GetMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<UserRole> getByUserIdAndRoleId(
            @PathVariable Long userId,
            @PathVariable Long roleId) {
        return userRoleService.findByUserIdAndRoleId(userId, roleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<UserRole>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(userRoleService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRole> update(@PathVariable Long id, @RequestBody UserRole userRole) {
        return userRoleService.findById(id)
                .map(existing -> {
                    userRole.setId(id);
                    return ResponseEntity.ok(userRoleService.update(userRole));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (userRoleService.findById(id).isPresent()) {
            userRoleService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

