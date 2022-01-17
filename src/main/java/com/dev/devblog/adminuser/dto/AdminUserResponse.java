package com.dev.devblog.adminuser.dto;

import com.dev.devblog.user.entity.User;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * @author :  Lee Hosung
 * @packageName : com.dev.devblog.adminuser.dto
 * @date : 2022-01-13
 * @description :
 * ===========================================================
 * DATE                  AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2022-01-13           Lee Hosung           최초 생성
 */

@Getter
public class AdminUserResponse {
    private final long userCode;
    private final String userId;
    private final String authority; //권한
    private final String nickName;
    private final String status;  //탈퇴 : N , 유지: Y
    private final LocalDateTime createDate;
    private final LocalDateTime passwordUpdateDate;
    private final String email;
    private final String accountPath; //가입루트 (이메일:email, 카카오: kakao, 네이버: naver)

    private AdminUserResponse(User user){
        this.userCode = user.getUserCode();
        this.userId = user.getUserId();
        this.authority = user.getAuthority();
        this.nickName = user.getNickName();
        this.status = user.getStatus();
        this.createDate = user.getCreateDate();
        this.passwordUpdateDate = user.getPasswordUpdateDate();
        this.email = user.getEmail();
        this.accountPath = user.getAccountPath();
    }

    public static AdminUserResponse from(User user) { return new AdminUserResponse(user); }

}
