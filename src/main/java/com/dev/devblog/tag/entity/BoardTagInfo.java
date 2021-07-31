package com.dev.devblog.tag.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "BOARD_TAGINFO")
public class BoardTagInfo implements Serializable {
    @Id
    private Long boardId;
    @Id
    private Long tagCode;

}
