package com.dev.devblog.comment;

import com.dev.devblog.comment.dto.CommentListResponse;
import com.dev.devblog.comment.dto.CommentSaveRequest;
import com.dev.devblog.comment.dto.CommentUpdateRequest;
import com.dev.devblog.comment.dto.ReplyCommentSaveRequest;
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
    public ResponseEntity<CommentListResponse> getList(@PathVariable Long boardId, @CurrentUserCode Long userCode) {
        CommentListResponse response = commentReadService.getList(boardId, userCode);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/comment/save")
    public ResponseEntity<String> commentSave(@RequestBody final CommentSaveRequest request, @CurrentUserCode Long userCode) {
        commentCUDService.save(request, userCode);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/comment/delete/{commentId}")
    public ResponseEntity<String> commentDelete(@PathVariable final Long commentId, @CurrentUserCode Long userCode) {
        commentCUDService.commentDelete(commentId, userCode);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/comment/update")
    public ResponseEntity<String> commentUpdate(@RequestBody final CommentUpdateRequest request, @CurrentUserCode Long userCode) {
        commentCUDService.commentUpdate(request, userCode);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comment/replySave")
    public ResponseEntity<String> replySave(@RequestBody final ReplyCommentSaveRequest request, @CurrentUserCode Long userCode) {
        commentCUDService.replySave(request, userCode);
        return ResponseEntity.ok().build();
    }
}
