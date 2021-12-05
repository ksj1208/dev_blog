package com.dev.devblog.category.service;

import com.dev.devblog.category.dao.CategoryRepository;
import com.dev.devblog.category.domain.Categories;
import com.dev.devblog.category.dto.CategorySaveRequest;
import com.dev.devblog.category.entity.Category;
import com.dev.devblog.profile.domain.Profiles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryCUDService {

	private final CategoryRepository categoryRepository;

	public void categorySave(CategorySaveRequest request, Long userCode) {
		categoryRepository.save(Categories.of(request).toCreateEntity());
	}
}
