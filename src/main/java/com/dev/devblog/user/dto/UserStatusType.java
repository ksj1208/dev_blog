package com.dev.devblog.user.dto;

import lombok.Getter;


@Getter
public enum UserStatusType {
    //정상, 휴면, 탈퇴
    ACTIVE("A"), INACTIVE("I"), DELETE("D");

    private final String value;

    UserStatusType(String value) { this.value = value;}
}
