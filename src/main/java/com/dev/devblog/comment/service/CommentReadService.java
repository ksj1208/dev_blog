package com.dev.devblog.comment.service;

import com.dev.devblog.comment.CommentRepository;
import com.dev.devblog.comment.dto.CommentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentReadService {

    private final CommentRepository commentRepository;

    public CommentListResponse getList(Long boardId) {
        return CommentListResponse.from(commentRepository.findAll());
    }
}
