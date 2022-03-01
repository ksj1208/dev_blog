package com.dev.devblog.adminuser.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminUserIndexController {
    @GetMapping("/admin/userListPage")
    public String profileListPage() {
        return "/admin/adminUser/adminUserList";
    }
}
