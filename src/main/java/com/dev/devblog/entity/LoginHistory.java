package com.dev.devblog.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "LOGIN_HISTORY")
public class LoginHistory {
    @Id
    private long historyId;

    @ManyToOne
    @JoinColumn(name = "user_code")
    private User userCode;

    @Column
    private String ipAddress;

    @Column
    private String browser;

    @Column
    private LocalDateTime accessTime;



}
