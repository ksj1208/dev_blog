package com.dev.devblog.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ReplyCommentSaveRequest {
    private Long commentId;
    private Long boardId;
    private String comment;
}
