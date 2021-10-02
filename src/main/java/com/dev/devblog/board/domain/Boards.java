package com.dev.devblog.board.domain;

import com.dev.devblog.board.dto.BoardSaveRequest;
import com.dev.devblog.board.dto.BoardStatusType;
import com.dev.devblog.board.entity.Board;
import com.dev.devblog.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Boards {
    private final String content;
    private final String subject;
    private final User user;

    private Boards(BoardSaveRequest request, User user) {
        this.content = request.getContent();
        this.subject = request.getSubject();
        this.user = user;
    }

    public static Boards of(BoardSaveRequest request, User user) {
        return new Boards(request, user);
    }

    public Board toCreateEntity() {
        return Board.builder()
                .user(this.user)
                .subject(this.subject)
                .content(this.content)
                .createDate(LocalDateTime.now())
                .status(BoardStatusType.ACTIVE.getValue())
                .hitCnt(0)
                .build();
    }
}
