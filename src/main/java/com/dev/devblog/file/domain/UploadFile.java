package com.dev.devblog.file.domain;

import lombok.Getter;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class UploadFile {

    private final MultipartFile file;
    private final Path path;
    @Getter
    private Path uploadFilePath;

    private UploadFile(MultipartFile file, Path path) {
        this.file = file;
        this.path = path;
    }

    public static UploadFile of(MultipartFile file, Path path) {
        return new UploadFile(file, path);
    }

    public Path upload() throws IOException {
        Files.createDirectories(path);
        createTargetPath();
        file.transferTo(uploadFilePath);
        return uploadFilePath;
    }

    private void createTargetPath() {
        String fileName = "test_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyymmddHHmmss"));
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        uploadFilePath = path.resolve(fileName + "." + extension);
    }
}
