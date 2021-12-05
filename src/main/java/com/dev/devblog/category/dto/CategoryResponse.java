package com.dev.devblog.category.dto;

import com.dev.devblog.category.entity.Category;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CategoryResponse {
    private final Long categoryId;
    private final String categoryName;
    private final String categoryStatus;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    private CategoryResponse(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.categoryStatus = category.getCategoryStatus();
        this.createDate = category.getCreateDate();
        this.updateDate = category.getUpdateDate();
    }

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category);
    }
}
