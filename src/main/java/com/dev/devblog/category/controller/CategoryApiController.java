package com.dev.devblog.category.controller;

import com.dev.devblog.category.dto.CategoryListResponse;
import com.dev.devblog.category.dto.CategorySaveRequest;
import com.dev.devblog.category.service.CategoryCUDService;
import com.dev.devblog.category.service.CategoryReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryReadService categoryReadService;
    private final CategoryCUDService categoryCUDService;

	@GetMapping("/categories/listAll")
	public ResponseEntity<CategoryListResponse> getList(final Pageable pageable){
		CategoryListResponse response = categoryReadService.getListAll(pageable);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/categories/list/{status}")
	public ResponseEntity<CategoryListResponse> getUseList(final Pageable pageable, @PathVariable String status){
		CategoryListResponse response = categoryReadService.getList(pageable, status);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/categories/save")
	public ResponseEntity<String> categorySave(@RequestBody final CategorySaveRequest request) {
		categoryCUDService.categorySave(request, 1L);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/categories/delete")
	public ResponseEntity<String> categoryDelete(@RequestBody final List<Long> categoryIdArr) {
		categoryCUDService.categoryDelete(categoryIdArr);
		return ResponseEntity.ok("삭제되었습니다.");
	}

	@PatchMapping("/categories/update")
	public ResponseEntity<String> categoryUpdate(@RequestBody final CategorySaveRequest request) {
		categoryCUDService.categoryUpdate(request);
		return ResponseEntity.ok().build();
	}

}
