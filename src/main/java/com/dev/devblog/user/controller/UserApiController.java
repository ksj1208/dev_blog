package com.dev.devblog.user.controller;

import com.dev.devblog.user.UserDetailServiceImpl;
import com.dev.devblog.user.dao.UserRepository;
import com.dev.devblog.user.dto.JoinMemberReqeust;
import com.dev.devblog.user.service.UserCUDService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserDetailServiceImpl userDetailServiceImpl;
    private final UserCUDService userCUDService;

    @PostMapping("/users/join")
    public ResponseEntity<String> userJoin(@RequestBody final JoinMemberReqeust request){
        log.info("가입호출 ");
        userCUDService.joinMember(request);
        log.info("가입끝");

        return ResponseEntity.ok().build();
    }

}
