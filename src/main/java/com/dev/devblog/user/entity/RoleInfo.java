package com.dev.devblog.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE_INFO")
public class RoleInfo {
    @Id
    private Long roleCode;
    @Column
    private String roleName;

}
