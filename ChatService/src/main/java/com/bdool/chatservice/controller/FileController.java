package com.bdool.chatservice.controller;

import com.bdool.chatservice.model.entity.FileEntity;
import com.bdool.chatservice.service.FileService;
import com.bdool.chatservice.service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;
    private final FileStorageService fileStorageService;

    @GetMapping("")
    public List<FileEntity> getAllFiles() {
        return fileStorageService.getAllFiles();
    }

    @PostMapping("/upload")
    public FileEntity uploadFile(@RequestParam("file") MultipartFile file, @RequestParam UUID profileId) {
        return fileService.uploadFile(file, profileId);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam UUID fileId, HttpServletRequest request) {
        return fileService.downloadFile(fileId, request);
    }

    @DeleteMapping("/delete/{fileId}")
    public ResponseEntity<?> deleteFile(@PathVariable UUID fileId) {
        fileService.deleteFile(fileId);
        return ResponseEntity.ok("File deleted successfully.");
    }
}


