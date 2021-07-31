package com.dev.devblog.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table (name= "USER")
public class User {
    @Id
    private long userCode;
    @Column
    private String userId;
    @Column
    private String password;
    @Column
    private String nickName;
    @Column
    private String status;
    @Column
    private LocalDateTime createDate;
    @Column
    private LocalDateTime passwordUpdateDate;




}
