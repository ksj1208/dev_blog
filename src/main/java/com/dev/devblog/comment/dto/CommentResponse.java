package com.dev.devblog.comment.dto;

import com.dev.devblog.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Getter
public class CommentResponse {

    private final Long boardId;
    private final Long commentId;
    private final String nickName;
    private final String comment;
    private final LocalDateTime createDate;
    private final Long pCommentId;
    private final String writeRole;
    private List<CommentResponse> pCommentList;

    private CommentResponse(Comment comment, Long readUserCode) {
        this.boardId = comment.getBoardId();
        this.commentId = comment.getCommentId();
        this.nickName = comment.getUser().getNickName();
        this.comment = comment.getContent();
        this.createDate = comment.getCreateDate();
        this.pCommentId = comment.getPCommentId();
        this.writeRole = comment.getUser().getUserCode() == readUserCode ? "Y" : "N";
    }

    public String getCreateDate() {
        String year = String.valueOf(createDate.getYear());
        String month = String.valueOf(createDate.getMonthValue());
        String day = String.valueOf(createDate.getDayOfMonth());
        String hour = String.valueOf(createDate.getHour() < 10 ? "0" + createDate.getHour() : createDate.getHour());
        String minute = String.valueOf(createDate.getMinute() < 10 ? "0" + createDate.getMinute() : createDate.getMinute());
        return year + "년 " + month + "월 " + day + "일 " + hour + ":" + minute;
    }

    public static CommentResponse from(Comment comment, Long readUserCode) {
        return new CommentResponse(comment, readUserCode);
    }

    public void setPCommentList(List<Comment> pCommentList, Long readUserCode) {
        this.pCommentList = pCommentList.stream().map((o) -> CommentResponse.from(o, readUserCode)).collect(Collectors.toList());
    }
}
