package com.dev.devblog.file.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@ConfigurationProperties(prefix = "file-path")
@Getter
@Setter
public class FilePath {
    private String root;
    private String image;

    public Path getEditorImageUploadPath() {
        return Paths.get(root + image);
    }
}
