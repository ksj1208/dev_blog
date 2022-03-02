package com.dev.devblog.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class JoinMemberRequest {
    private String userId;
    private String nickName;
    private String password;
    private String email;


}
