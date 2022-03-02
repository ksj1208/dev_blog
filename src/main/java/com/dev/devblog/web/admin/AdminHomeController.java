package com.dev.devblog.web.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class AdminHomeController {

    @GetMapping("/admin_login")
    public String homeView() {
        return "admin/admin_main";
    }


}
