package com.dev.devblog.user.login.controller;

import com.dev.devblog.user.login.service.LoginReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginIndexController {
    private final LoginReadService loginReadService;

    @GetMapping("/user/login/signup")
    public String goSignup() {
        return "/pages/signup";
    }

}
