package com.dev.devblog.category.service;

import com.dev.devblog.category.dao.CategoryRepository;
import com.dev.devblog.category.domain.Categories;
import com.dev.devblog.category.dto.CategorySaveRequest;
import com.dev.devblog.category.entity.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryCUDService {

	private final CategoryRepository categoryRepository;

	public void categorySave(CategorySaveRequest request) {
		Long count = categoryRepository.findByCategoryName(request.getCategoryName());

		if (count != 0) throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");

		categoryRepository.save(Categories.of(request).toCreateEntity());
	}

	public void categoryDelete(List<Long> categoryIdArr) {
		for(Long categoryId : categoryIdArr) {
			categoryRepository.findById(categoryId).orElseThrow(
					() -> new NoSuchElementException("삭제할 카테고리가 존재하지 않습니다.")
			);

			categoryRepository.deleteById(categoryId);
		}
	}

	public void categoryUpdate(CategorySaveRequest request) {
		Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(
				() -> new NoSuchElementException("수정할 카테고리가 존재하지 않습니다.")
		);

		category.updateCategoryName(request.getCategoryName());
		category.updateCategoryStatus(request.getCategoryStatus());
		category.updateDate(LocalDateTime.now());
	}
}
