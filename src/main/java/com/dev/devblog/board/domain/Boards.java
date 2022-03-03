package com.dev.devblog.board.domain;

import com.dev.devblog.board.dto.BoardSaveRequest;
import com.dev.devblog.board.dto.BoardStatusType;
import com.dev.devblog.board.entity.Board;
import com.dev.devblog.category.entity.Category;
import com.dev.devblog.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class Boards {
    private final String content;
    private final String subject;
    private final User user;
    private final Category category;

    private Boards(BoardSaveRequest request, User user, Category category) {
        this.content = request.getContent();
        this.subject = request.getSubject();
        this.user = user;
        this.category = category;
    }

    public static Boards of(BoardSaveRequest request, User user, Category category) {
        return new Boards(request, user, category);
    }

    public Board toCreateEntity() {
        return Board.builder()
                .user(this.user)
                .category(this.category)
                .subject(this.subject)
                .content(this.content)
                .createDate(LocalDateTime.now())
                .status(BoardStatusType.ACTIVE.getValue())
                .hitCnt(0)
                .build();
    }
}
