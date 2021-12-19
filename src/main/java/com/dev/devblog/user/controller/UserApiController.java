package com.dev.devblog.user.controller;

import com.dev.devblog.user.UserDetailServiceImpl;
import com.dev.devblog.user.UserRepository;
import com.dev.devblog.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserRepository userRepository;
    private final UserDetailServiceImpl userDetailServiceImpl;

}
