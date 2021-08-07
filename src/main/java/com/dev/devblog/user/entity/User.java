package com.dev.devblog.user.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table (name= "USER")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long userCode;
    @Column
    private String userId;
    @Column
    private String password;
    @ManyToOne
    @JoinColumn(name = "ROLE_CODE")
    private RoleInfo roleCode;
    @Column
    private String nickName;
    @Column
    private String status;
    @Column
    private LocalDateTime createDate;
    @Column
    private LocalDateTime passwordUpdateDate;

    public User(){
    }



    public User(Long userCode, String userId, String password){
        this.userCode = userCode;
        this.userId = userId;
        this.password = password;
    }

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
    }

}
