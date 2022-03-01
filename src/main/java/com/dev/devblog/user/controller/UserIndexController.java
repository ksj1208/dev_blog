package com.dev.devblog.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserIndexController {

    @GetMapping("/users/signup")
    public String goJoinPage(){
        return "/pages/signup";
    }


}
