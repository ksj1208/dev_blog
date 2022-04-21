package com.dev.devblog.board.service;

import com.dev.devblog.board.dao.BoardRepository;
import com.dev.devblog.board.dto.BoardListResponse;
import com.dev.devblog.board.dto.BoardResponse;
import com.dev.devblog.board.dto.SearchBoardListRequest;
import com.dev.devblog.board.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;



@Service
@RequiredArgsConstructor
public class BoardReadService {

    private final BoardRepository boardRepository;

    public BoardListResponse getList(Pageable pageable, SearchBoardListRequest request, String status) {
        if(! status.equals("All"))
            return BoardListResponse.from(boardRepository.findAllByContentContaining("", pageable, status));

        if(isRequestNull(request.getCategoryId()) && isRequestNull(request.getTagCode())) {
            return BoardListResponse.from(boardRepository.findAllByContaining("", pageable));
        }

        if(isRequestNull(request.getCategoryId())) {
            return BoardListResponse.from(boardRepository.findAllByContaining("", pageable, request.getCategoryId()));
        }

        if(isRequestNull(request.getTagCode())) {
            return BoardListResponse.from(boardRepository.findAllByContaining("", pageable, request.getCategoryId()));
        }

        return BoardListResponse.from(boardRepository.findAllByContaining("", pageable));
    }

    public BoardResponse getDetail(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new NoSuchElementException("해당 게시글이 존재하지않습니다.")
        );

        return BoardResponse.from(board);
    }

    public Long findCategoryIdByBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new NoSuchElementException()
        );

        return board.getCategory().getCategoryId();
    }

    private boolean isRequestNull(Long id) {
        return id == null;
    }
}
