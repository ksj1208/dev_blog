package com.dev.devblog.category.dto;

import com.dev.devblog.category.entity.Category;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CategoryDtoResponse {
    private final Long categoryId;
    private final String categoryName;
    private final String categoryStatus;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    private CategoryDtoResponse(CategoryDto categoryDto) {
        this.categoryId = categoryDto.getCategoryId();
        this.categoryName = categoryDto.getCategoryName();
        this.categoryStatus = categoryDto.getCategoryStatus();
        this.createDate = categoryDto.getCreateDate();
        this.updateDate = categoryDto.getUpdateDate();
    }

    public static CategoryDtoResponse from(CategoryDto categoryDto) {
        return new CategoryDtoResponse(categoryDto);
    }
}
