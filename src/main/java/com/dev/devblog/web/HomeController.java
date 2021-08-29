package com.dev.devblog.web;

import com.dev.devblog.user.UserDetailServiceImpl;
import com.dev.devblog.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserDetailServiceImpl userDetailServiceImpl;

    @GetMapping("/")
    public String homeView() {
        return "pages/home";
    }

    @GetMapping("/login")
    public String loginView() {

        return "pages/login";
    }

    @GetMapping("/signup")
    public String signupView() {
        return "pages/signup";
    }

    @PostMapping("/signup")
    public String signup(User user) {
        userDetailServiceImpl.createNew(user);
        return "redirect:/";
    }

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
