package com.dev.devblog.user.controller;

import com.dev.devblog.user.dao.UserRepository;
import com.dev.devblog.user.service.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserRepository userRepository;
    private final UserDetailServiceImpl userDetailServiceImpl;

}
