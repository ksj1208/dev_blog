package com.dev.devblog.board.entity;

import com.dev.devblog.board.entity.Board;
import com.dev.devblog.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table (name = "LIKE_INFO")
public class LikeInfo implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "board_id")
    private Board boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code")
    private User userCode;

}
