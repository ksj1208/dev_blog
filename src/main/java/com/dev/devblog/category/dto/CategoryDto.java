package com.dev.devblog.category.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryDto {

	private Long categoryId;
	private String categoryName;
	private String categoryStatus;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;

	@QueryProjection
	public CategoryDto(Long categoryId, String categoryName, String categoryStatus, LocalDateTime createDate, LocalDateTime updateDate) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryStatus = categoryStatus;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
}
