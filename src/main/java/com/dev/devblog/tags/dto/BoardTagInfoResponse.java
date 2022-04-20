package com.dev.devblog.tags.dto;

import com.dev.devblog.tag.entity.BoardTagInfo;
import lombok.Getter;

@Getter
public class BoardTagInfoResponse {

    private Long tagCode;
    private String tagName;

    private BoardTagInfoResponse(BoardTagInfo boardTagInfo) {
        this.tagName = boardTagInfo.getTagCode().getTagName();
        this.tagCode = boardTagInfo.getTagCode().getTagId();
    }

    public static BoardTagInfoResponse from(BoardTagInfo boardTagInfo) {
        return new BoardTagInfoResponse(boardTagInfo);
    }
}
