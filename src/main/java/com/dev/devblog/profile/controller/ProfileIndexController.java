package com.dev.devblog.profile.controller;

import com.dev.devblog.board.dto.BoardResponse;
import com.dev.devblog.profile.dto.ProfileResponse;
import com.dev.devblog.profile.service.ProfileReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProfileIndexController {

    private final ProfileReadService profileReadService;

    @GetMapping("/admin/profile/listPage")
    public String profileListPage() {
        return "/admin/profile/profileList";
    }

    @GetMapping("/admin/profile/profileWriter")
    public String profileWritePage() {
        return "/admin/profile/profileWriter";
    }


    @GetMapping("/admin/profile/detailPage/{profileCode}")
    public String userBoardDetailPage(@PathVariable Long profileCode, Model model) {
        log.info("================= 프로필 조회 시작 =================");
        ProfileResponse response = profileReadService.getDetail(profileCode);
        model.addAttribute("profile", response);
        log.info("================= 프로필 조회 끝 =================");
        log.info("");
        log.info("");
        log.info("");
        return "/admin/profile/profileDetail";
    }

}
