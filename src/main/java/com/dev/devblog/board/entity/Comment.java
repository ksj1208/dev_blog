package com.dev.devblog.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "COMMENT")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private Long commentId;

    @Column(name = "BOARD_ID")
    private Long boardId;

    @Column(name = "PCOMMENT_ID")
    private Long pCommentId;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "USER_CODE")
    private Long userCode;
}
