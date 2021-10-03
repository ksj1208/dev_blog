package com.dev.devblog.board.dto;

import com.dev.devblog.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {
    private final Long boardId;
    private final String subject;
    private final String content;
    private final String status;
    private final LocalDateTime createDate;
    private final String writer;

    private BoardResponse(Board board) {
        this.boardId = board.getBoardId();
        this.content = board.getContent();
        this.subject = board.getSubject();
        this.status = board.getStatus();
        this.createDate = board.getCreateDate();
        this.writer = board.getUser().getNickName();
    }

    public static BoardResponse from(Board board) {
        return new BoardResponse(board);
    }
}
