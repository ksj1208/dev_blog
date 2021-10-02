package com.dev.devblog.file;

import com.dev.devblog.file.domain.FilePath;
import com.dev.devblog.file.domain.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService fileService;

    @PostMapping("/files/imageUpload")
    public ResponseEntity<String> editorImageUpload(@ModelAttribute MultipartFile file) throws IOException {
        fileService.editorImageUpload(file);

        return ResponseEntity.ok(fileService.editorImageUpload(file));
    }
}
