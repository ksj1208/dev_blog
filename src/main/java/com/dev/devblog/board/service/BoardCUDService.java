package com.dev.devblog.board.service;

import com.dev.devblog.board.dao.BoardRepository;
import com.dev.devblog.board.domain.Boards;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCUDService {

    private final BoardRepository boardRepository;

    public void boardSave(Boards boards) {
        boardRepository.save(boards.createBoard());
    }
}
