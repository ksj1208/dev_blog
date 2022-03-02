package com.dev.devblog.comment;

import com.dev.devblog.comment.dto.CommentSaveRequest;
import com.dev.devblog.comment.dto.ReplyCommentSaveRequest;
import com.dev.devblog.comment.entity.Comment;
import com.dev.devblog.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class Comments {
    private Long commentId;
    private final Long boardId;
    private final Long userCode;
    private final String comment;
    private final LocalDateTime createDate;

    private Comments(Long boardId, Long userCode, String comment, LocalDateTime createDate) {
        this.boardId = boardId;
        this.userCode = userCode;
        this.comment = comment;
        this.createDate = createDate;
    }

    private Comments(Long boardId, Long commentId, Long userCode, String comment, LocalDateTime createDate) {
        this.boardId = boardId;
        this.commentId = commentId;
        this.userCode = userCode;
        this.comment = comment;
        this.createDate = createDate;
    }

    public static Comments ofComment(CommentSaveRequest request, Long userCode) {
        return new Comments(request.getBoardId(), userCode, request.getComment(), LocalDateTime.now());
    }

    public static Comments ofReplyComment(ReplyCommentSaveRequest request, Long userCode) {
        return new Comments(request.getBoardId(), request.getCommentId(), userCode, request.getComment(), LocalDateTime.now());
    }

    public Comment toCreateEntity(User user) {
        return Comment.builder()
                .boardId(this.boardId)
                .user(user)
                .content(this.comment)
                .createDate(this.createDate)
                .pCommentId(this.commentId)
                .build();
    }
}
