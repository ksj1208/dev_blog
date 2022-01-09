package com.dev.devblog.user.domain;

import com.dev.devblog.user.dto.JoinMemberReqeust;
import com.dev.devblog.user.dto.UserStatusType;
import com.dev.devblog.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Users {

    private final String userId;
    private final String password;
    private final String nickName;
    private final String email;

    private Users(JoinMemberReqeust request){
        this.userId = request.getUserId();
        this.password = request.getPassword();
        this.nickName = request.getNickName();
        this.email = request.getEmail();
    }

    public static Users of(JoinMemberReqeust request){
        return new Users(request);
    }

    public User toCreateEntity(){
        return User.builder()
                .userId(this.userId)
                .password(this.password)
                .authority("user")
                .nickName(this.nickName)
                .status(UserStatusType.ACTIVE.getValue())
                .createDate(LocalDateTime.now())
                .email(this.email)
                .accountPath("email")
                .build();

    }
}
