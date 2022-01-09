package com.dev.devblog.tags.dto;

import com.dev.devblog.tags.entity.Tags;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TagsResponse {
    private final Long tagId;
    private final String tagName;
    private final String tagStatus;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    private TagsResponse(Tags tag) {
        this.tagId = tag.getTagId();
        this.tagName = tag.getTagName();
        this.tagStatus = tag.getTagStatus();
        this.createDate = tag.getCreateDate();
        this.updateDate = tag.getUpdateDate();
    }

    public static TagsResponse from(Tags tag) {
        return new TagsResponse(tag);
    }
}
