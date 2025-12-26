package com.teach.teachingsys.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadService {

    @Value("${file.upload.path:uploads}")
    private String uploadPath;

    @Value("${file.upload.max-size:104857600}") // 默认100MB
    private long maxFileSize;

    /**
     * 上传文件
     * @param file 文件
     * @param category 文件分类 (avatar, course-material, qualification-proof等)
     * @return 文件访问URL
     */
    public String uploadFile(MultipartFile file, String category) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空");
        }

        if (file.getSize() > maxFileSize) {
            throw new RuntimeException("文件大小超过限制");
        }

        // 创建分类目录
        String dateDir = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String categoryPath = uploadPath + "/" + category + "/" + dateDir;
        File categoryDir = new File(categoryPath);
        if (!categoryDir.exists()) {
            categoryDir.mkdirs();
        }

        // 生成唯一文件名
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String filename = UUID.randomUUID().toString() + extension;

        // 保存文件
        Path targetPath = Paths.get(categoryPath, filename);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        // 返回访问URL
        String fileUrl = "/uploads/" + category + "/" + dateDir + "/" + filename;
        log.info("文件上传成功: {}", fileUrl);
        return fileUrl;
    }

    /**
     * 删除文件
     */
    public boolean deleteFile(String fileUrl) {
        try {
            if (fileUrl == null || !fileUrl.startsWith("/uploads/")) {
                return false;
            }
            
            String filePath = uploadPath + fileUrl.substring("/uploads".length());
            Path path = Paths.get(filePath);
            
            if (Files.exists(path)) {
                Files.delete(path);
                log.info("文件删除成功: {}", filePath);
                return true;
            }
            return false;
        } catch (IOException e) {
            log.error("删除文件失败: {}", fileUrl, e);
            return false;
        }
    }

    /**
     * 获取文件大小
     */
    public long getFileSize(String fileUrl) {
        try {
            if (fileUrl == null || !fileUrl.startsWith("/uploads/")) {
                return 0;
            }
            
            String filePath = uploadPath + fileUrl.substring("/uploads".length());
            Path path = Paths.get(filePath);
            
            if (Files.exists(path)) {
                return Files.size(path);
            }
            return 0;
        } catch (IOException e) {
            log.error("获取文件大小失败: {}", fileUrl, e);
            return 0;
        }
    }
}

