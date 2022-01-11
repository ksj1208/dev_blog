package com.dev.devblog.comment.service;

import com.dev.devblog.comment.CommentRepository;
import com.dev.devblog.comment.dto.CommentListResponse;
import com.dev.devblog.comment.dto.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentReadService {

    private final CommentRepository commentRepository;

    public CommentListResponse getList(Long boardId, Long userCode) {
        CommentListResponse response = CommentListResponse.from(commentRepository.findAllByBoardId(boardId), userCode);

        for(CommentResponse comment : response.getCommentList()) {
            comment.setPCommentList(commentRepository.findAllById(comment.getCommentId()), userCode);
        }

        return response;
    }
}
