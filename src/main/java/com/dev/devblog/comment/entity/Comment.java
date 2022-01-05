package com.dev.devblog.comment.entity;

import com.dev.devblog.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "COMMENT")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Column(name = "BOARD_ID")
    private Long boardId;

    @Column(name = "PCOMMENT_ID")
    private Long pCommentId;

    @Column(name = "CONTENT")
    private String content;

    @JoinColumn(name = "USER_CODE", referencedColumnName = "userCode")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
}
