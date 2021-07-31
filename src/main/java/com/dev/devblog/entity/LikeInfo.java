package com.dev.devblog.entity;

import javax.persistence.*;

@Entity
@Table (name = "LIKE_INFO")
public class LikeInfo {
    @Id
    @JoinColumn (name = "board_id")
    private Board boardId;

    @ManyToOne
    @JoinColumn(name = "user_code")
    private User userCode;

}
