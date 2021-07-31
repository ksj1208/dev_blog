package com.dev.devblog.entity;

import javax.persistence.*;

@Entity
@Table (name = "EMAIL_AUTH")
public class EmailAuth {
    @Id
    private long authId;

    @ManyToOne
    @JoinColumn (name = "user_code")
    private User userCode;

    @Column
    private String authCode;

}
