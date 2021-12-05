package com.dev.devblog.category.dto;


import com.dev.devblog.category.entity.Category;
import com.dev.devblog.profile.dto.ProfileResponse;
import com.dev.devblog.profile.entity.BlogProfile;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

@Getter
@Slf4j
public class CategoryListResponse {

    private final Page<CategoryResponse> categoryList;

    private CategoryListResponse(Page<Category> list) {
        this.categoryList = list.map(CategoryResponse::from);
        log.info(categoryList.toString());
    }

    public static CategoryListResponse from(Page<Category> list) {
        return new CategoryListResponse(list);
    }
}
