package com.dev.devblog.board.controller;


import com.dev.devblog.board.dto.*;
import com.dev.devblog.board.service.BoardCUDService;
import com.dev.devblog.board.service.BoardReadService;
import com.dev.devblog.grobal.annotation.CurrentUserCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardReadService boardReadService;
    private final BoardCUDService boardCUDService;

    @GetMapping("/boards/list/{status}")
    public ResponseEntity<BoardListResponse> getList(Pageable pageable, @ModelAttribute SearchBoardListRequest request, @PathVariable String status) {
        BoardListResponse response = boardReadService.getList(pageable, request, status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/boards/detail/{boardId}")
    public ResponseEntity<BoardResponse> getDetail(@PathVariable Long boardId) {
        BoardResponse response = boardReadService.getDetail(boardId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/boards/save")
    public ResponseEntity<String> boardSave(@CurrentUserCode Long userCode, @RequestBody final BoardSaveRequest request) {
        boardCUDService.boardSave(request, userCode);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/boards/update")
    public ResponseEntity<String> boardUpdate(@RequestBody final BoardSaveRequest request) {
        boardCUDService.boardUpdate(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/boards/status")
    public ResponseEntity<String> boardUpdateStatus(@RequestBody final BoardUpdateStatusRequest request) {
        boardCUDService.boardStatusUpdate(request);
        return ResponseEntity.ok("변경이 완료되었습니다.");
    }

    @DeleteMapping("/boards/delete")
    public ResponseEntity<String> boardDelete(@RequestBody final List<Long> boardIdArr) {
        boardCUDService.boardDelete(boardIdArr);
        return ResponseEntity.ok("삭제되었습니다.");
    }
}
