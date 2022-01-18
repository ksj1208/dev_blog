package com.dev.devblog.category.dao;

import com.dev.devblog.category.dto.CategoryDto;
import com.dev.devblog.category.dto.QCategoryDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;


import java.util.List;

import static com.dev.devblog.category.entity.QCategory.*;
import static org.springframework.util.StringUtils.*;

public class CategoryRepositoryImpl implements CategoryDynamicRepository {

	private final JPAQueryFactory queryFactory;

	public CategoryRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Page<CategoryDto> findAllByStatus(Pageable pageable, String status) {
		QueryResults<CategoryDto> results = queryFactory
				.select(new QCategoryDto(
						category.categoryId,
						category.categoryName,
						category.categoryStatus,
						category.createDate,
						category.updateDate
				))
				.from(category)
				.where(statusEq(status))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();

		List<CategoryDto> content = results.getResults();
		long total = results.getTotal();
		return new PageImpl<>(content, pageable, total);
	}

	private BooleanExpression statusEq(String status) {
		return hasText(status) ? category.categoryStatus.eq(status) : null;
	}
}
