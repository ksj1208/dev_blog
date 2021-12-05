package com.dev.devblog.category.service;

import com.dev.devblog.category.dao.CategoryRepository;
import com.dev.devblog.category.dto.CategoryListResponse;
import com.dev.devblog.profile.dao.ProfileRepository;
import com.dev.devblog.profile.dto.ProfileListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryReadService {

	private final CategoryRepository categoryRepository;

	public CategoryListResponse getList(Pageable pageable){
		return CategoryListResponse.from(categoryRepository.findAllByPageable(pageable));
	}
}
