package com.dev.devblog.hs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BoardTagInfo {
    @Id
    private Long boardId;
    @Id
    private Long tagCode;

}
