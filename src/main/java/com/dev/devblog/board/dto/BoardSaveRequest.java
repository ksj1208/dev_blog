package com.dev.devblog.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BoardSaveRequest {
    private Long boardId;
    private String content;
    private String subject;
}
