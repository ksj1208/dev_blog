package com.dev.devblog.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchBoardListRequest {
    private Long categoryId;
    private Long tagCode;
}
