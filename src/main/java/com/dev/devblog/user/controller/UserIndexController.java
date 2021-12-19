package com.dev.devblog.user.controller;

import com.dev.devblog.user.service.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserIndexController {
    private final UserReadService userReadService;

    @GetMapping("/users/singup")
    public String goJoinPage(){
        return "/pages/singup";
    }


}
