package com.dev.devblog.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
public class BoardSaveRequest {
    private Long boardId;
    private String content;
    private String subject;
    private Long categoryId;
    private Map<String, Long> tagList;
}
