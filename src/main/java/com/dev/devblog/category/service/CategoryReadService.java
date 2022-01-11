package com.dev.devblog.category.service;

import com.dev.devblog.category.dao.CategoryRepository;
import com.dev.devblog.category.dto.CategoryListResponse;
import com.dev.devblog.category.dto.CategoryResponse;
import com.dev.devblog.category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryReadService {

	private final CategoryRepository categoryRepository;

	public CategoryListResponse getList(Pageable pageable, String status){
		return CategoryListResponse.from(categoryRepository.findAllByPageableAndStatus(pageable, status));
	}

	public CategoryResponse getDetail(Long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(
				() -> new NoSuchElementException("해당 카테고리가 존재하지 않습니다.")
		);
		return CategoryResponse.from(category);
	}

	public CategoryListResponse getListAll(Pageable pageable) {
		return CategoryListResponse.from(categoryRepository.findAllByPageable(pageable));
	}
}
