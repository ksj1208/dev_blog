package com.dev.devblog.category.dto;


import com.dev.devblog.category.entity.Category;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

@Getter
@Slf4j
public class CategoryDtoListResponse {

    private final Page<CategoryDtoResponse> categoryList;

    private CategoryDtoListResponse(Page<CategoryDto> list) {
        this.categoryList = list.map(CategoryDtoResponse::from);
        log.info(categoryList.toString());
    }

    public static CategoryDtoListResponse from(Page<CategoryDto> list) {
        return new CategoryDtoListResponse(list);
    }
}
