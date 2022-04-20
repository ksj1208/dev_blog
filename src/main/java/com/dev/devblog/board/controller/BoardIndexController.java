package com.dev.devblog.board.controller;

import com.dev.devblog.board.dto.BoardResponse;
import com.dev.devblog.board.service.BoardReadService;
import com.dev.devblog.category.dto.CategoryAllListResponse;
import com.dev.devblog.category.dto.CategoryResponse;
import com.dev.devblog.category.service.CategoryReadService;
import com.dev.devblog.tags.dto.TagAllListResponse;
import com.dev.devblog.tags.service.TagsReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequiredArgsConstructor
public class BoardIndexController {

    private final BoardReadService boardReadService;
    private final CategoryReadService categoryReadService;
    private final TagsReadService tagsReadService;

    @GetMapping("/admin/board/listPage")
    public String adminBoardListPage(Model model) {
        return "/admin/board/boardList";
    }

    @GetMapping("/admin/board/writePage")
    public String adminBoardWritePage(Model model) {
        CategoryAllListResponse response = categoryReadService.findAllByStatus("사용");
        model.addAttribute("categoryList", response.getCategoryList());
        return "/admin/board/boardWrite";
    }

    @GetMapping("/admin/board/updatePage/{boardId}")
    public String adminBoardUpdatePage(@PathVariable Long boardId, Model model) {
        CategoryAllListResponse response = categoryReadService.findAllByStatus("사용");
        TagAllListResponse tagAllListResponse = tagsReadService.findByBoardId(boardId);
        Long categoryId = boardReadService.findCategoryIdByBoardId(boardId);

        model.addAttribute("categoryList", response.getCategoryList());
        model.addAttribute("boardId", boardId);
        model.addAttribute("tagList", tagAllListResponse.getList());
        model.addAttribute("cateGoryId", categoryId);
        return "/admin/board/boardUpdate";
    }

    @GetMapping("/user/board/detailPage/{boardId}")
    public String userBoardDetailPage(@PathVariable Long boardId, Model model) {
        BoardResponse response = boardReadService.getDetail(boardId);
        TagAllListResponse tagAllListResponse = tagsReadService.findByBoardId(boardId);

        model.addAttribute("board", response);
        model.addAttribute("tagList", tagAllListResponse.getList());

        return "/user/board/boardDetail";
    }
}
