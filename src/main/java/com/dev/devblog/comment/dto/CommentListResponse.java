package com.dev.devblog.comment.dto;

import com.dev.devblog.comment.entity.Comment;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentListResponse {
    private final List<CommentResponse> commentList;

    private CommentListResponse(List<Comment> list) {
        this.commentList = list.stream().map(CommentResponse::from).collect(Collectors.toList());
    }

    public static CommentListResponse from(List<Comment> list) {
        return new CommentListResponse(list);
    }
}
