package com.dev.devblog.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Table(name = "BOARD_ATTACHMENT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardAttachment {

    @Id
    private Long attCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
}