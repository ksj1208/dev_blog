package com.dev.devblog.oauth.kakao;

import com.dev.devblog.user.UserDetailServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequiredArgsConstructor
public class KakaoAPIController {
    private final KakaoService kakaoService;
    private final UserDetailServiceImpl userDetailService;

    @GetMapping("/kakao/callback") // kakao/callbaack
    @ResponseBody
    public String kakaoCallback(String code){
        //토큰가져오기
        OAuthToken oAuthToken = kakaoService.getKakaoToken(code);

        //사용자 정보 가져오기
        KakaoProfile kakaoProfile = kakaoService.getKakaoUserInfo(oAuthToken);

        return kakaoProfile.getKakao_account().getEmail();
    }
}
