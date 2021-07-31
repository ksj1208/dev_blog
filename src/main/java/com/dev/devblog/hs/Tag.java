package com.dev.devblog.hs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Tag {
    @Id
    private Long tagCode;
    @Column
    private String tagName;
    @Column
    private Date createDate;
    @Column
    private String status;

}
