package com.dev.devblog.user.login.service;

import com.dev.devblog.user.login.dao.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginReadService {
    private final LoginRepository loginRepository;
}
