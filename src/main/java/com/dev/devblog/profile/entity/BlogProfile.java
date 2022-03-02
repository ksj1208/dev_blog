package com.dev.devblog.profile.entity;

import com.dev.devblog.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@Getter
@Entity
@Table (name = "BLOG_PROFILE")
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@RestController
public class BlogProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long profileCode;

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

    public void updateStatus(String status) {

        this.status = status;
    }
    public void updateSubject(String subject) {

        this.subject = subject;
    }
    public void updateContent(String content) {

        this.content = content;
    }
    public void updateDate(LocalDateTime updateDate) {

        this.updateDate = updateDate;
    }
}
