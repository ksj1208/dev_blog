package com.dev.devblog.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategorySaveRequest {
	private String categoryName;
	private String categoryStatus;
}
