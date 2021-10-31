package com.dev.devblog.board.dto;

import com.dev.devblog.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public String getCreateDate() {
        String year = String.valueOf(createDate.getYear());
        String month = String.valueOf(createDate.getMonthValue());
        String day = String.valueOf(createDate.getDayOfMonth());
        String hour = String.valueOf(createDate.getHour() < 10 ? "0" + createDate.getHour() : createDate.getHour());
        String minute = String.valueOf(createDate.getMinute() < 10 ? "0" + createDate.getMinute() : createDate.getMinute());
        return year + "년 " + month + "월 " + day + "일 " + hour + ":" + minute;
    }

    public static BoardResponse from(Board board) {
        return new BoardResponse(board);
    }
}
