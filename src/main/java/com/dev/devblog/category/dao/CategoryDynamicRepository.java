package com.dev.devblog.category.dao;

import com.dev.devblog.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryDynamicRepository {

	Page<Category> findAllByStatus(Pageable pageable, String status);
}
