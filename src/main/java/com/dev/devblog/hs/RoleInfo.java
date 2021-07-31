package com.dev.devblog.hs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoleInfo {
    @Id
    private Long roleCode;
    @Column
    private String roleName;

}
