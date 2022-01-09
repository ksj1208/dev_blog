package com.dev.devblog.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table (name= "USER")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long userCode;
    @Column
    private String userId;
    @Column
    private String password;
    @Column
    private String authority; //권한
    @Column
    private String nickName;
    @Column
    private String status;  //탈퇴 : N , 유지: Y
    @Column
    private LocalDateTime createDate;
    @Column
    private LocalDateTime passwordUpdateDate;
    @Column
    private String email;
    @Column
    private String accountPath; //가입루트 (이메일:email, 카카오: kakao, 네이버: naver)

    public User(){
    }

    public User(String userId, String password){
        this.userId = userId;
        this.password = password;
    }

    public User(Long userCode, String userId, String password){
        this.userCode = userCode;
        this.userId = userId;
        this.password = password;
    }

    public User(Long userCode, String userId, String password, String email, String nickName, String accountPath){
        this.userCode = userCode;
        this.userId = userId;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.accountPath = accountPath;
    }


}
