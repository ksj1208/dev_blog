package com.dev.devblog.profile.dto;

import lombok.Getter;

@Getter
public enum ProfileStatusType {
    ACTIVE("A"), INACTIVE("I"), DELETE("D");

    private final String value;

    ProfileStatusType(String value) {
        this.value = value;
    }

}
