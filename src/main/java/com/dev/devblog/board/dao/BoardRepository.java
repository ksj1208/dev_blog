package com.dev.devblog.board.dao;

import com.dev.devblog.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "SELECT b FROM Board b" +
            " WHERE b.content LIKE %:searchContent%" +
            " AND b.status IN (:status)" +
            " ORDER BY b.createDate DESC ")
    Page<Board> findAllByContentContaining(@Param("searchContent") String searchContent, Pageable pageable, @Param("status") String status);

    @Query(value = "SELECT b FROM Board b" +
            " WHERE b.content LIKE %:searchContent%" +
            " ORDER BY b.createDate DESC ")
    Page<Board> findAllByContaining(@Param("searchContent") String searchContent, Pageable pageable);
}
