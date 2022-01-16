package com.dev.devblog.tags.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TagsSaveRequest {
	private Long tagId;
	private String tagName;
	private String tagStatus;
}
