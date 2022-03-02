package com.dev.devblog.comment;

import com.dev.devblog.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT c FROM Comment c" +
            " WHERE c.boardId = :boardId" +
            " AND c.pCommentId IS NULL" +
            " ORDER BY c.createDate ASC ")
    List<Comment> findAllByBoardId(Long boardId);

    @Query(value = "SELECT c FROM Comment c" +
            " WHERE c.pCommentId = :commentId" +
            " ORDER BY c.createDate ASC ")
    List<Comment> findAllById(Long commentId);
}
