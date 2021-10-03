package com.dev.devblog.board.service;

import com.dev.devblog.board.dao.BoardRepository;
import com.dev.devblog.board.dto.BoardListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardReadService {

    private final BoardRepository boardRepository;

    public BoardListResponse getList(Pageable pageable) {
        return BoardListResponse.from(boardRepository.findAllByContentContaining("",pageable));
    }
}
