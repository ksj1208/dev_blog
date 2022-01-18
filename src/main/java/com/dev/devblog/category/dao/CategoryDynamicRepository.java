package com.dev.devblog.category.dao;

import com.dev.devblog.category.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryDynamicRepository {

	Page<CategoryDto> findAllByStatus(Pageable pageable, String status);
}
