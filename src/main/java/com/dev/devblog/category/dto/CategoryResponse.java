package com.dev.devblog.category.dto;

import com.dev.devblog.category.entity.Category;
import com.dev.devblog.profile.entity.BlogProfile;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CategoryResponse {
    private final Long categoryId;
    private final String categoryName;
    private final LocalDateTime createDate;

    private CategoryResponse(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.createDate = category.getCreateDate();
    }

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category);
    }
}
