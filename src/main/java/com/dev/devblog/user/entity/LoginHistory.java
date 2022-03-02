package com.dev.devblog.user.entity;

import com.dev.devblog.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table (name = "LOGIN_HISTORY")
public class LoginHistory {
    @Id
    private long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code")
    private User userCode;

    @Column
    private String ipAddress;

    @Column
    private String browser;

    @Column
    private LocalDateTime accessTime;



}
