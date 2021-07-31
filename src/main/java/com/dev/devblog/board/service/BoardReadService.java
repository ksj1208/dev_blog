package com.dev.devblog.board.service;

import com.dev.devblog.board.dao.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardReadService {

    private final BoardRepository boardRepository;

}
