package com.dev.devblog.category.domain;

import com.dev.devblog.board.domain.Boards;
import com.dev.devblog.board.dto.BoardSaveRequest;
import com.dev.devblog.board.dto.BoardStatusType;
import com.dev.devblog.board.entity.Board;
import com.dev.devblog.category.dto.CategorySaveRequest;
import com.dev.devblog.category.entity.Category;
import com.dev.devblog.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Categories {
	private final String categoryName;
	private final String categoryStatus;

	private Categories(CategorySaveRequest request) {
		this.categoryName = request.getCategoryName();
		this.categoryStatus = request.getCategoryStatus();
	}

	public static Categories of(CategorySaveRequest request) {
		return new Categories(request);
	}

	public Category toCreateEntity() {
		return Category.builder()
				.categoryName(this.categoryName)
				.categoryStatus(this.categoryStatus)
				.createDate(LocalDateTime.now())
				.build();
	}
}
