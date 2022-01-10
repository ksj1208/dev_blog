package com.dev.devblog.tags.controller;

import com.dev.devblog.tags.dto.TagsResponse;
import com.dev.devblog.tags.service.TagsReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class TagsIndexController {

	private final TagsReadService tagsReadService;

	@GetMapping("/tags/tagMain")
	public String main() {
		return "admin/tag/tagMain";
	}

	@GetMapping("/tags/tagListPage")
	public String tagListPage() {
		return "/admin/tag/tagList";
	}

	@GetMapping("/tags/tagWrite")
	public String tagWritePage() {
		return "/admin/tag/tagWrite";
	}

	@GetMapping("/tags/detailPage/{tagId}")
	public String tagDetailPage(@PathVariable Long tagId, Model model) {
		TagsResponse response = tagsReadService.getDetail(tagId);
		model.addAttribute("tag", response);
		return "/admin/tag/tagDetail";
	}
}
