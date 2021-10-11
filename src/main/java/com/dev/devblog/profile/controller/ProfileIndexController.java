package com.dev.devblog.profile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProfileIndexController {

    @GetMapping("/profile/listPage")
    public String profileListPage() {
        return "/admin/profile/profileList";
    }

    @GetMapping("/profile/profileWriter")
    public String profileWritePage() {
        return "/admin/profile/profileWriter";
    }

}
