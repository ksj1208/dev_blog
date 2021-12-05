package com.dev.devblog.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryIndexController {

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
}
