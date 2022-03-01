package com.dev.devblog.user.domain;

import com.dev.devblog.user.dto.JoinMemberRequest;
import com.dev.devblog.user.dto.UserStatusType;
import com.dev.devblog.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Getter
public class Users {

    private final String userId;
    private final String password;
    private final String nickName;
    private final String email;

    @Builder
    private Users(JoinMemberRequest request){
        this.userId = request.getUserId();
        this.password = request.getPassword();
        this.nickName = request.getNickName();
        this.email = request.getEmail();
    }

    public static Users from(JoinMemberRequest request){
        return new Users(request);
    }

    public User toCreateEntity(){
        return User.builder()
                .userId(this.userId)
                .password(new BCryptPasswordEncoder().encode(this.password)) //password 암호화
                .authority("ROLE_USER")
                .nickName(this.nickName)
                .status(UserStatusType.ACTIVE.getValue())
                .createDate(LocalDateTime.now())
                .email(this.email)
                .accountPath("email")
                .build();

    }

}
