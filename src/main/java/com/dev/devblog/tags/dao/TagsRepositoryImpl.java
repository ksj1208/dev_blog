package com.dev.devblog.tags.dao;

import com.dev.devblog.tags.dto.QTagsDto;
import com.dev.devblog.tags.dto.TagsDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static com.dev.devblog.category.entity.QCategory.category;
import static com.dev.devblog.tags.entity.QTags.*;
import static org.springframework.util.StringUtils.hasText;


public class TagsRepositoryImpl implements TagsDynamicRepository{

	private final JPAQueryFactory queryFactory;

	public TagsRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Page<TagsDto> findAllByStatus(Pageable pageable, String status) {
		QueryResults<TagsDto> results = queryFactory
				.select(new QTagsDto(
						tags.tagId,
						tags.tagName,
						tags.tagStatus,
						tags.createDate,
						tags.updateDate
				))
				.from(tags)
				.where(statusEq(status))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();

		List<TagsDto> content = results.getResults();
		long total = results.getTotal();
		return new PageImpl<>(content, pageable, total);
	}

	private BooleanExpression statusEq(String status) {
		return hasText(status) ? tags.tagStatus.eq(status) : null;
	}
}
