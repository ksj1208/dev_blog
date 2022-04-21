package com.dev.devblog.web;

import com.dev.devblog.board.dto.BoardListResponse;
import com.dev.devblog.board.dto.SearchBoardListRequest;
import com.dev.devblog.board.service.BoardReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;



@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardReadService boardReadService;
    private final static int LIMIT_PAGE_COUNT = 5;

    @GetMapping("/main")
    public String homeView(@PageableDefault(size = 2) Pageable pageable, @ModelAttribute SearchBoardListRequest request, Model model) {
        BoardListResponse response = boardReadService.getList(pageable, request, "All");

        int minPage = (int) Math.floor((pageable.getPageNumber()) / LIMIT_PAGE_COUNT);
        int startPage = minPage * LIMIT_PAGE_COUNT + 1;
        int endPage = (minPage + 1) * LIMIT_PAGE_COUNT;
        int totalPage = response.getBoardList().getTotalPages();

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage > totalPage ? totalPage : endPage);
        model.addAttribute("categoryId", request.getCategoryId());
        model.addAttribute("boardList", response.getBoardList());
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", pageable.getPageNumber() + 1);

        return "main";
    }


    @GetMapping("/login")
    public String loginView() {

        return "pages/login";
    }

//    @GetMapping("/signup")
//    public String signupView() {
//        return "pages/signup";
//    }

  /*  @PostMapping("/signup")
    public String signup(User user) {
        userDetailServiceImpl.createNew(user);
        return "redirect:/";
    }*/

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/member/info")
    public String userInfoView() {
        return "pages/user_info";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminView() {
        return "pages/admin";
    }

    @GetMapping("/denied")
    public String deniedView() {
        return "pages/denied";
    }




}
