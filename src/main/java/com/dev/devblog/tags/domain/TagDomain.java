package com.dev.devblog.tags.domain;

import com.dev.devblog.tags.dto.TagsSaveRequest;
import com.dev.devblog.tags.entity.Tags;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TagDomain {
	private final String tagName;
	private final String tagStatus;

	private TagDomain(TagsSaveRequest request) {
		this.tagName = request.getTagName();
		this.tagStatus = request.getTagStatus();
	}

	public static TagDomain of(TagsSaveRequest request) {
		return new TagDomain(request);
	}

	public Tags toCreateEntity() {
		return Tags.builder()
				.tagName(this.tagName)
				.tagStatus(this.tagStatus)
				.createDate(LocalDateTime.now())
				.updateDate(LocalDateTime.now())
				.build();
	}
}
