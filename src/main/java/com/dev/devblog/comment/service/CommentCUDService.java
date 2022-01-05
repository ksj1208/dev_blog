package com.dev.devblog.comment.service;

import com.dev.devblog.board.dao.BoardRepository;
import com.dev.devblog.comment.CommentRepository;
import com.dev.devblog.comment.Comments;
import com.dev.devblog.comment.dto.CommentSaveRequest;
import com.dev.devblog.user.UserRepository;
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
        Comments comments = Comments.of(request, userCode);

        boardRepository.findById(comments.getBoardId()).orElseThrow(
                () -> new NoSuchElementException("게시글이 존재하지 않습니다.")
        );
        User user =userRepository.findById(userCode).orElseThrow(
                () -> new NoSuchElementException("작성할 유저정보가 존재하지 않습니다.")
        );

        commentRepository.save(comments.toCreateEntity(user));
    }
}
