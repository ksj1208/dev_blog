package com.dev.devblog.admin.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ADMIN_USER")
public class AdminUser {

    @Id
    private Long adminCode;
    @ManyToOne
    @JoinColumn(name = "ROLE_CODE")
    private RoleInfo roleCode;
    @Column
    private String adminId;
    @Column
    private String password;
    @Column
    private String nickName;
    @Column
    private LocalDateTime createDate;
    @Column
    private String status;

}
