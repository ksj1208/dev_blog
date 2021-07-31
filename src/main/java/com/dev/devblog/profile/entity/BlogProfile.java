package com.dev.devblog.profile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table (name = "BLOG_PROFILE")
public class BlogProfile {
    @Id
    private Long profileCode;
    @Column
    private String content;
    @Column
    private LocalDateTime createDate;
}
