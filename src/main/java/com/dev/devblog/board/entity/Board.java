package com.dev.devblog.board.entity;

import com.dev.devblog.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "BOARD")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@RestController
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId;

    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @JoinColumn(name = "USER_CODE", referencedColumnName = "userCode")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private String subject;
    @Column
    private String content;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;
    @Column
    private String status;

    @Column(name = "HIT_CNT")
    private long hitCnt;
}