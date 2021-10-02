package com.dev.devblog.board.dto;

import lombok.Getter;

@Getter
public enum BoardStatusType {
    ACTIVE("A"), INACTIVE("I"), DELETE("D");

    private final String value;

    BoardStatusType(String value) {
        this.value = value;
    }
}
