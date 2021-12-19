package com.dev.devblog.user.service;

import com.dev.devblog.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReadService {
    private final UserRepository userRepository;
}
