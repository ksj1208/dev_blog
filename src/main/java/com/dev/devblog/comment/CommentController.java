package com.dev.devblog.comment;

import com.dev.devblog.comment.dto.CommentListResponse;
import com.dev.devblog.comment.dto.CommentSaveRequest;
import com.dev.devblog.comment.service.CommentCUDService;
import com.dev.devblog.comment.service.CommentReadService;
import com.dev.devblog.grobal.annotation.CurrentUserCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentReadService commentReadService;
    private final CommentCUDService commentCUDService;

    @GetMapping("/comment/list/{boardId}")
    public ResponseEntity<CommentListResponse> getList(@PathVariable Long boardId) {
        CommentListResponse response = commentReadService.getList(boardId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/comment/save")
    public ResponseEntity<String> commentSave(@RequestBody final CommentSaveRequest request, @CurrentUserCode Long userCode) {
        commentCUDService.save(request, userCode);
        return ResponseEntity.ok().build();
    }
}
