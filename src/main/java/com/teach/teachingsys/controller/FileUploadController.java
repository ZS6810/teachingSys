package com.teach.teachingsys.controller;

import com.teach.teachingsys.dto.ApiResponse;
import com.teach.teachingsys.service.FileUploadService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    /**
     * 上传头像
     */
    @PostMapping("/avatar")
    public ResponseEntity<ApiResponse<Map<String, String>>> uploadAvatar(
            @RequestParam("file") MultipartFile file,
            HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error("请先登录"));
            }

            // 验证文件类型
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("只能上传图片文件"));
            }

            String fileUrl = fileUploadService.uploadFile(file, "avatar");
            long fileSize = fileUploadService.getFileSize(fileUrl);

            Map<String, String> result = new HashMap<>();
            result.put("url", fileUrl);
            result.put("size", String.valueOf(fileSize));

            return ResponseEntity.ok(ApiResponse.success("上传成功", result));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("上传失败: " + e.getMessage()));
        }
    }

    /**
     * 上传课程资料
     */
    @PostMapping("/course-material")
    public ResponseEntity<ApiResponse<Map<String, String>>> uploadCourseMaterial(
            @RequestParam("file") MultipartFile file,
            HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error("请先登录"));
            }

            String fileUrl = fileUploadService.uploadFile(file, "course-material");
            long fileSize = fileUploadService.getFileSize(fileUrl);

            Map<String, String> result = new HashMap<>();
            result.put("url", fileUrl);
            result.put("size", String.valueOf(fileSize));
            result.put("originalName", file.getOriginalFilename());

            return ResponseEntity.ok(ApiResponse.success("上传成功", result));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("上传失败: " + e.getMessage()));
        }
    }

    /**
     * 上传教师资质证明
     */
    @PostMapping("/qualification-proof")
    public ResponseEntity<ApiResponse<Map<String, String>>> uploadQualificationProof(
            @RequestParam("file") MultipartFile file,
            HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error("请先登录"));
            }

            // 验证文件类型（PDF或图片）
            String contentType = file.getContentType();
            if (contentType == null || 
                (!contentType.equals("application/pdf") && !contentType.startsWith("image/"))) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("只能上传PDF或图片文件"));
            }

            String fileUrl = fileUploadService.uploadFile(file, "qualification-proof");
            long fileSize = fileUploadService.getFileSize(fileUrl);

            Map<String, String> result = new HashMap<>();
            result.put("url", fileUrl);
            result.put("size", String.valueOf(fileSize));

            return ResponseEntity.ok(ApiResponse.success("上传成功", result));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("上传失败: " + e.getMessage()));
        }
    }

    /**
     * 上传课程封面
     */
    @PostMapping("/course-cover")
    public ResponseEntity<ApiResponse<Map<String, String>>> uploadCourseCover(
            @RequestParam("file") MultipartFile file,
            HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error("请先登录"));
            }

            // 验证文件类型
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("只能上传图片文件"));
            }

            String fileUrl = fileUploadService.uploadFile(file, "course-cover");
            long fileSize = fileUploadService.getFileSize(fileUrl);

            Map<String, String> result = new HashMap<>();
            result.put("url", fileUrl);
            result.put("size", String.valueOf(fileSize));

            return ResponseEntity.ok(ApiResponse.success("上传成功", result));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("上传失败: " + e.getMessage()));
        }
    }

    /**
     * 删除文件
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteFile(@RequestParam String fileUrl) {
        try {
            boolean deleted = fileUploadService.deleteFile(fileUrl);
            if (deleted) {
                return ResponseEntity.ok(ApiResponse.success("删除成功", null));
            } else {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("文件不存在或删除失败"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("删除失败: " + e.getMessage()));
        }
    }
}

