package com.dev.devblog.comment.service;

import com.dev.devblog.board.dao.BoardRepository;
import com.dev.devblog.comment.CommentRepository;
import com.dev.devblog.comment.Comments;
import com.dev.devblog.comment.dto.CommentSaveRequest;
import com.dev.devblog.comment.dto.CommentUpdateRequest;
import com.dev.devblog.comment.dto.ReplyCommentSaveRequest;
import com.dev.devblog.comment.entity.Comment;
import com.dev.devblog.user.dao.UserRepository;
import com.dev.devblog.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentCUDService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public void save(CommentSaveRequest request, Long userCode) {
        Comments comments = Comments.ofComment(request, userCode);

        boardRepository.findById(comments.getBoardId()).orElseThrow(
                () -> new NoSuchElementException("게시글이 존재하지 않습니다.")
        );
        User user =userRepository.findById(userCode).orElseThrow(
                () -> new NoSuchElementException("작성할 유저정보가 존재하지 않습니다.")
        );

        commentRepository.save(comments.toCreateEntity(user));
    }

    public void commentDelete(Long commentId, Long currentUserCode) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NoSuchElementException("삭제할 댓글 정보가 없습니다.")
        );

        if(! currentUserCode.equals(comment.getUser().getUserCode()))
            throw new IllegalArgumentException("잘못된 요청정보 입니다.");

        commentRepository.deleteById(commentId);
    }

    public void commentUpdate(CommentUpdateRequest request, Long currentUserCode) {
        Comment comment = commentRepository.findById(request.getCommentId()).orElseThrow(
                () -> new NoSuchElementException("삭제할 댓글 정보가 없습니다.")
        );

        if(! currentUserCode.equals(comment.getUser().getUserCode()))
            throw new IllegalArgumentException("잘못된 요청정보 입니다.");

        comment.updateContent(request.getComment());
    }

    public void replySave(ReplyCommentSaveRequest request, Long userCode) {
        Comments comments = Comments.ofReplyComment(request, userCode);

        commentRepository.findById(request.getCommentId()).orElseThrow(
                () -> new NoSuchElementException("답글 할 댓글의 정보가 존재하지 않습니다.")
        );
        User user =userRepository.findById(userCode).orElseThrow(
                () -> new NoSuchElementException("작성할 유저정보가 존재하지 않습니다.")
        );

        commentRepository.save(comments.toCreateEntity(user));
    }
}
