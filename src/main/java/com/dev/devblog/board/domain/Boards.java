package com.dev.devblog.board.domain;

import com.dev.devblog.board.dto.BoardSaveRequest;
import com.dev.devblog.board.entity.Board;
import lombok.Getter;

@Getter
public class Boards {

    private final Long boardId;

    private Boards(BoardSaveRequest request) {
        this.boardId = request.getBoardId();
    }

    public static Boards of(BoardSaveRequest request) {
        return new Boards(request);
    }

    public Board createBoard() {
        return Board.builder()
                .boardId(this.boardId)
                .build();
    }
}
