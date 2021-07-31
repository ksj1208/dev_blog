package com.dev.devblog.board.controller;

import com.dev.devblog.board.domain.Boards;
import com.dev.devblog.board.dto.BoardSaveRequest;
import com.dev.devblog.board.service.BoardCUDService;
import com.dev.devblog.board.service.BoardReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardReadService boardReadService;
    private final BoardCUDService boardCUDService;

    @GetMapping("/boards/list/{userCode}")
    public ResponseEntity<String> getList(@PathVariable final Long userCode) {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/boards/save")
    public ResponseEntity<String> boardSave(final BoardSaveRequest request) {
        //값이 많거나 또는 통계쪽이면 도메인 필드가 많아지게 되는 문제가 있음
        //dto를 바로 넘겨서 서비스에서 도메인을 생성하는 방식이 좋음.
        boardCUDService.boardSave(Boards.of(request));

        return ResponseEntity.ok().build();
    }
}
