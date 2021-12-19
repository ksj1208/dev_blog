package com.dev.devblog.user.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class UserDto {
    private long userCode;
    private String userId;
    private String password;
    private RoleInfo roleCode;
    private String nickName;
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime passwordUpdateDate;

    public User toEntity(){
        return new User(userCode, userId, password);
    }
}

