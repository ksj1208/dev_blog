package com.dev.devblog.board.dao;

import com.dev.devblog.tag.entity.BoardTagInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardTagInfoRepository extends JpaRepository<BoardTagInfo, Long> {

    List<BoardTagInfo> findAllByBoardId(Long boardId);
}
