package com.dev.devblog.board.controller;

import com.dev.devblog.board.domain.Boards;
import com.dev.devblog.board.dto.BoardSaveRequest;
import com.dev.devblog.board.service.BoardCUDService;
import com.dev.devblog.board.service.BoardReadService;
import com.dev.devblog.grobal.annotation.CurrentUserCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> boardSave(@RequestBody final BoardSaveRequest request) {
        //TODO 유저 detail 만들어지면 userCode 넣어야함.
        boardCUDService.boardSave(request, 1L);

        return ResponseEntity.ok().build();
    }
}
