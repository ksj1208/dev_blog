package com.dev.devblog.user.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    AMDIN("ROLE_ADMIN");
//    USER("ROLE_USER");

    private String value;
}
