package com.dev.devblog.tags.dto;

import com.dev.devblog.tags.entity.Tags;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class TagsDtoResponse {
    private final Long tagId;
    private final String tagName;
    private final String tagStatus;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    private TagsDtoResponse(TagsDto tagsDto) {
        this.tagId = tagsDto.getTagId();
        this.tagName = tagsDto.getTagName();
        this.tagStatus = tagsDto.getTagStatus();
        this.createDate = tagsDto.getCreateDate();
        this.updateDate = tagsDto.getUpdateDate();
    }

    public static TagsDtoResponse from(TagsDto tagsDto) {
        return new TagsDtoResponse(tagsDto);
    }
}
