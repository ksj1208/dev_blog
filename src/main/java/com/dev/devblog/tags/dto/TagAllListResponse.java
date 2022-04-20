package com.dev.devblog.tags.dto;

import com.dev.devblog.tag.entity.BoardTagInfo;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TagAllListResponse {

    private final List<BoardTagInfoResponse> list;

    private TagAllListResponse(List<BoardTagInfo> list) {
        this.list = list.stream().map(BoardTagInfoResponse::from).collect(Collectors.toList());
    }

    public static TagAllListResponse from(List<BoardTagInfo> list) {
        return new TagAllListResponse(list);
    }
}
