package com.dev.devblog.hs;

import javax.persistence.*;
import java.util.Date;

@Entity
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
    private Date createDate;
    @Column
    private String status;

}
