package com.dev.devblog.tag.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "TAG")
public class Tag {
    @Id
    private Long tagCode;
    @Column
    private String tagName;
    @Column
    private LocalDateTime createDate;
    @Column
    private String status;

}
