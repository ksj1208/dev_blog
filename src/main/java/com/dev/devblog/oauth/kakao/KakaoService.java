package com.dev.devblog.oauth.kakao;

import com.dev.devblog.user.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {
    private final KakaoRepository kakaoRepository;

    public OAuthToken getKakaoToken(String code) {
        //post방식으로 key=value 데이터를 카카오쪽으로 요청)
        //RestTmplate lib 사용
        RestTemplate rt = new RestTemplate();
        //HttpHeaders object 생성
        HttpHeaders headers = new HttpHeaders();
        //헤더에 바디 데이터가 key:value임을 알려줌
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody object 생성  //설정파일로 분리
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "a3ba113686e820ae50156cdc2f58daac");
        params.add("redirect_uri", "http://localhost:8080/kakao/callback");
        params.add("code", code);

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        //kakao로 토근요청
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token", //요청주소
                HttpMethod.POST,//요청방법
                kakaoTokenRequest,//요청값
                String.class//응답받을 타입
        );

        //json으로 넘어온 token 오브젝트화
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;

        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        log.info("kakao access token: " + oAuthToken.getAccess_token());

        return oAuthToken;

    }

    public KakaoProfile getKakaoUserInfo(OAuthToken oAuthToken) {
        //엑세스 토큰으로 사용자 정보 요청
        RestTemplate rt = new RestTemplate();
        //HttpHeaders object 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
                new HttpEntity<>(headers);

        //kakao로 토근요청
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me", //요청주소
                HttpMethod.POST,//요청방법
                kakaoProfileRequest2,//요청값
                String.class//응답받을 타입
        );

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfile kakaoProfile = null;

        try {
            kakaoProfile = objectMapper.readValue(response.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        log.info("kakao userId: " + kakaoProfile.getId());
        log.info("kakao email: " + kakaoProfile.getKakao_account().getEmail());

        log.info("userName: " + kakaoProfile.getKakao_account().getEmail()+"_" + kakaoProfile.getId());
        log.info("userEmail: " + kakaoProfile.getKakao_account().getEmail());
        //UUID tempPassword = UUID.randomUUID();

        User user = User.builder()
                .userId(String.valueOf(kakaoProfile.getId()))
                .nickName(kakaoProfile.getProperties().getNickname())
                .email(kakaoProfile.getKakao_account().getEmail())
                .build();

        return kakaoProfile;
    }
}
