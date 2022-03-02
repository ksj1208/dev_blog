package com.dev.devblog.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateStatusRequest {
    private Long boardId;
    private String status;
}
