package com.dev.devblog.category.controller;

import com.dev.devblog.category.dto.CategoryDtoListResponse;
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

	@GetMapping("/categories")
	public ResponseEntity<CategoryListResponse> getList(final Pageable pageable){
		CategoryListResponse response = categoryReadService.getList(pageable);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/categories/{status}")
	public ResponseEntity<CategoryDtoListResponse> getUseList(final Pageable pageable, @PathVariable String status){
		System.out.println("status = " + status);
		CategoryDtoListResponse response = categoryReadService.findAllByStatus(pageable, status);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/categories")
	public ResponseEntity<String> categorySave(@RequestBody final CategorySaveRequest request) {
		categoryCUDService.categorySave(request, 1L);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/categories")
	public ResponseEntity<String> categoryDelete(@RequestBody final List<Long> categoryIdArr) {
		categoryCUDService.categoryDelete(categoryIdArr);
		return ResponseEntity.ok("삭제되었습니다.");
	}

	@PatchMapping("/categories")
	public ResponseEntity<String> categoryUpdate(@RequestBody final CategorySaveRequest request) {
		categoryCUDService.categoryUpdate(request);
		return ResponseEntity.ok().build();
	}

}
