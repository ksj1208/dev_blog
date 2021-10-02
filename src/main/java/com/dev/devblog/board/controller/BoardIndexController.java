package com.dev.devblog.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BoardIndexController {

    @GetMapping("/admin/board/listPage")
    public String adminBoardListPage() {
        return "/admin/board/boardList";
    }

    @GetMapping("/admin/board/writePage")
    public String adminBoardWirtePage() {
        return "/admin/board/boardWrite";
    }
}
