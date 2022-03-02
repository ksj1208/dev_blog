package com.dev.devblog.tags.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
public class TagsDto {

	private Long tagId;
	private String tagName;
	private String tagStatus;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;

	@QueryProjection
	public TagsDto(Long tagId, String tagName, String tagStatus, LocalDateTime createDate, LocalDateTime updateDate) {
		this.tagId = tagId;
		this.tagName = tagName;
		this.tagStatus = tagStatus;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
}