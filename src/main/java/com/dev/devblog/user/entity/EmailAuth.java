package com.dev.devblog.user.entity;

import com.dev.devblog.user.entity.User;

import javax.persistence.*;


@Entity
@Table (name = "EMAIL_AUTH")
public class EmailAuth {
    @Id
    private Long authId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_code")
    private User userCode;

    @Column
    private String authCode;

}
