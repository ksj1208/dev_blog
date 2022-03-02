package com.dev.devblog.tags.dto;


import com.dev.devblog.tags.entity.Tags;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;


@Getter
@Slf4j
public class TagsDtoListResponse {

    private final Page<TagsDtoResponse> tagList;

    private TagsDtoListResponse(Page<TagsDto> list) {
        this.tagList = list.map(TagsDtoResponse::from);
        log.info(tagList.toString());
    }

    public static TagsDtoListResponse from(Page<TagsDto> list) {
        return new TagsDtoListResponse(list);
    }
}
