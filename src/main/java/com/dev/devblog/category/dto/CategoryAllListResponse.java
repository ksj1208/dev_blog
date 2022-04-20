package com.dev.devblog.category.dto;

import com.dev.devblog.category.entity.Category;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoryAllListResponse {

    private final List<CategoryResponse> categoryList;

    private CategoryAllListResponse(List<Category> list) {
        this.categoryList = list.stream().map(CategoryResponse::from).collect(Collectors.toList());
    }

    public static CategoryAllListResponse from(List<Category> list) {
        return new CategoryAllListResponse(list);
    }
}
