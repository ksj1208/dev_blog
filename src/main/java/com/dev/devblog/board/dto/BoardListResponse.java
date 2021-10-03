package com.dev.devblog.board.dto;

import com.dev.devblog.board.entity.Board;
import lombok.Getter;
import org.springframework.data.domain.Page;


@Getter
public class BoardListResponse {
    private final Page<BoardResponse> boardList;

    private BoardListResponse(Page<Board> list) {
        this.boardList = list.map(BoardResponse::from);
    }

    public static BoardListResponse from(Page<Board> list) {
        return new BoardListResponse(list);
    }
}
