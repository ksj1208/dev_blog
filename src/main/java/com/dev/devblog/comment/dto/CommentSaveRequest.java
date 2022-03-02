package com.dev.devblog.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CommentSaveRequest {
    private String comment;
    private Long boardId;
}
