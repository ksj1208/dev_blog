package com.dev.devblog.file;

import com.dev.devblog.file.domain.FilePath;
import com.dev.devblog.file.domain.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;


@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    private final FilePath filePath;

    public String editorImageUpload(MultipartFile file) throws IOException {
        Path imagePath = filePath.getEditorImageUploadPath();
        UploadFile uploadFile = UploadFile.of(file, imagePath);
        uploadFile.upload();
        return filePath.getImage() + uploadFile.getUploadFilePath().getFileName();
    }
}
