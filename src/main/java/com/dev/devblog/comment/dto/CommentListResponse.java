package com.dev.devblog.comment.dto;

import com.dev.devblog.comment.entity.Comment;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentListResponse {
    private final List<CommentResponse> commentList;

    private CommentListResponse(List<Comment> list, Long userCode) {
        this.commentList = list.stream().map((o) -> CommentResponse.from(o, userCode)).collect(Collectors.toList());
    }

    public static CommentListResponse from(List<Comment> list, Long userCode) {
        return new CommentListResponse(list, userCode);
    }
}
