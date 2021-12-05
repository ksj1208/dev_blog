package com.dev.devblog.category.controller;

import com.dev.devblog.category.dto.CategoryListResponse;
import com.dev.devblog.category.dto.CategorySaveRequest;
import com.dev.devblog.category.entity.Category;
import com.dev.devblog.category.service.CategoryCUDService;
import com.dev.devblog.category.service.CategoryReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryReadService categoryReadService;
    private final CategoryCUDService categoryCUDService;

	@GetMapping("/categories/list")
	public ResponseEntity<CategoryListResponse> getList(final Pageable pageable){
		CategoryListResponse response = categoryReadService.getList(pageable);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/categories/save")
	public ResponseEntity<String> categorySave(@RequestBody final CategorySaveRequest request) {
		categoryCUDService.categorySave(request, 1L);

		return ResponseEntity.ok().build();
	}

}
