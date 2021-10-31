package com.dev.devblog.board.service;

import com.dev.devblog.board.dao.BoardRepository;
import com.dev.devblog.board.domain.Boards;
import com.dev.devblog.board.dto.BoardSaveRequest;
import com.dev.devblog.board.dto.BoardUpdateStatusRequest;
import com.dev.devblog.board.entity.Board;
import com.dev.devblog.user.UserRepository;
import com.dev.devblog.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardCUDService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public void boardSave(BoardSaveRequest request, Long userCode) {
        User user = userRepository.findById(userCode).orElseThrow(
                () -> new NoSuchElementException("게시글 작성 회원을 찾을 수 없습니다.")
        );

        boardRepository.save(Boards.of(request, user).toCreateEntity());
    }

    public void boardStatusUpdate(BoardUpdateStatusRequest request) {
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow(
                () -> new NoSuchElementException("변경할 게시글을 찾을 수 없습니다")
        );

        board.updateStatus(request.getStatus());
    }
}
