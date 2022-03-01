package com.dev.devblog.tags.controller;

import com.dev.devblog.tags.dto.TagsDtoListResponse;
import com.dev.devblog.tags.dto.TagsDtoResponse;
import com.dev.devblog.tags.dto.TagsListResponse;
import com.dev.devblog.tags.dto.TagsSaveRequest;
import com.dev.devblog.tags.service.TagsCUDService;
import com.dev.devblog.tags.service.TagsReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagsApiController {

    private final TagsReadService tagsReadService;
    private final TagsCUDService tagsCUDService;

	@GetMapping("/tags")
	public ResponseEntity<TagsListResponse> getList(final Pageable pageable){
		TagsListResponse response = tagsReadService.findAll(pageable);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/tags/{status}")
	public ResponseEntity<TagsDtoListResponse> getList(final Pageable pageable, @PathVariable String status){
		TagsDtoListResponse response = tagsReadService.findAllByStatus(pageable, status);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/tags")
	public ResponseEntity<String> tagSave(@RequestBody final TagsSaveRequest request) {
		tagsCUDService.tagSave(request, 1L);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/tags")
	public ResponseEntity<String> tagDelete(@RequestBody final List<Long> tagIdArr) {
		tagsCUDService.tagDelete(tagIdArr);
		return ResponseEntity.ok("삭제되었습니다.");
	}

	@PatchMapping("/tags")
	public ResponseEntity<String> tagUpdate(@RequestBody final TagsSaveRequest request) {
		tagsCUDService.tagUpdate(request);
		return ResponseEntity.ok().build();
	}

}
