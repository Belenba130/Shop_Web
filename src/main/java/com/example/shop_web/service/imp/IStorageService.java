package com.example.shop_web.service.imp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class IStorageService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(uploadDir, fileName);
        Files.copy(file.getInputStream(), path);
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileName)
                .toUriString();
    }

    public Resource downloadFile(String fileName) throws MalformedURLException {
        Path path = Paths.get(uploadDir).resolve(fileName);
        Resource resource = new UrlResource(path.toUri());
        if (!resource.exists()) {
            throw new IllegalArgumentException("File not found");
        }

        return resource;
    }
}

