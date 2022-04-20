package com.dev.devblog.tag.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.IdClass;
import java.io.Serializable;

@IdClass(BoardTagInfo.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardTagInfoPK implements Serializable {
    private Long boardId;
    private Long tagCode;
}
