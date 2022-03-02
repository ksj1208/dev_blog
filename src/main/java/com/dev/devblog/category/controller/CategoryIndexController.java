package com.dev.devblog.category.controller;

import com.dev.devblog.category.dto.CategoryResponse;
import com.dev.devblog.category.service.CategoryReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
public class CategoryIndexController {

	private final CategoryReadService categoryReadService;

	@GetMapping("/category/categoryMain")
	public String main() {
		return "admin/category/categoryMain";
	}

	@GetMapping("/category/categoryListPage")
	public String categoryListPage() {
		return "/admin/category/categoryList";
	}

	@GetMapping("/category/categoryWrite")
	public String categoryWritePage() {
		return "/admin/category/categoryWrite";
	}

	@GetMapping("/category/detailPage/{categoryId}")
	public String categoryDetailPage(@PathVariable Long categoryId, Model model) {
		CategoryResponse response = categoryReadService.getDetail(categoryId);
		model.addAttribute("category", response);
		return "/admin/category/categoryDetail";
	}
}
