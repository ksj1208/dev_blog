package com.dev.devblog.tag.entity;

import com.dev.devblog.tags.entity.Tags;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BoardTagInfoPK.class)
@Table(name = "BOARD_TAGINFO")
public class BoardTagInfo implements Serializable {
    @Id
    private Long boardId;
    @Id
    @JoinColumn(name = "TAG_CODE", referencedColumnName = "TAG_ID")
    @ManyToOne
    private Tags tagCode;

    public static BoardTagInfo toEntity(Long boardId, Tags tagId) {
        return BoardTagInfo.builder()
                .boardId(boardId)
                .tagCode(tagId)
                .build();
    }
}
