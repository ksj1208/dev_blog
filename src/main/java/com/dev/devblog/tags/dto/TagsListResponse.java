package com.dev.devblog.tags.dto;


import com.dev.devblog.tags.entity.Tags;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;


@Getter
@Slf4j
public class TagsListResponse {

    private final Page<TagsResponse> tagList;

    private TagsListResponse(Page<Tags> list) {
        this.tagList = list.map(TagsResponse::from);
        log.info(tagList.toString());
    }

    public static TagsListResponse from(Page<Tags> list) {
        return new TagsListResponse(list);
    }
}
