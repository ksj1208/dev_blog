package com.dev.devblog.user.service;


import com.dev.devblog.user.dao.UserRepository;
import com.dev.devblog.user.domain.Users;
import com.dev.devblog.user.dto.JoinMemberReqeust;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCUDService {

    private final UserRepository userRepository;


    public void joinMember(JoinMemberReqeust request) {
        userRepository.save(Users.of(request).toCreateEntity());
    }
}
