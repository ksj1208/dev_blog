package com.dev.devblog.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CategorySaveRequest {
	private Long categoryId;
	private String categoryName;
	private String categoryStatus;
}
