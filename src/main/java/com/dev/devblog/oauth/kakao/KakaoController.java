package com.dev.devblog.oauth.kakao;

import com.dev.devblog.user.UserDetailServiceImpl;
import com.dev.devblog.user.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.HttpHeadersReturnValueHandler;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoService kakaoService;
    private final UserDetailServiceImpl userDetailService;

    @GetMapping("/kakaocallback")
    @ResponseBody
    public String kakaoCallback(String code){

        //post방식으로 key=value 데이터를 카카오쪽으로 요청)
        //RestTmplate lib 사용
        RestTemplate rt = new RestTemplate();
        //HttpHeaders object 생성
        HttpHeaders headers = new HttpHeaders();
        //헤더에 바디 데이터가 key:value임을 알려줌
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody object 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "a3ba113686e820ae50156cdc2f58daac");
        params.add("redirect_uri", "http://localhost:8080/kakaocallback");
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


        //엑세스 토큰으로 사용자 정보 요청
        RestTemplate rt2 = new RestTemplate();
        //HttpHeaders object 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
                new HttpEntity<>(headers2);

        //kakao로 토근요청
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me", //요청주소
                HttpMethod.POST,//요청방법
                kakaoProfileRequest2,//요청값
                String.class//응답받을 타입
        );

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;

        try {
            kakaoProfile = objectMapper.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        log.info("kakao userId: " + kakaoProfile.getId());
        log.info("kakao email: " + kakaoProfile.getKakao_account().getEmail());

        log.info("userName: " + kakaoProfile.getKakao_account().getEmail()+"_" + kakaoProfile.getId());
        log.info("userEmail: " + kakaoProfile.getKakao_account().getEmail());
        UUID tempPassword = UUID.randomUUID();
        log.info("userPassword: " + tempPassword);

        User user = User.builder()
                .userId(String.valueOf(kakaoProfile.getId()))
                .password(tempPassword.toString())
                .email(kakaoProfile.getKakao_account().getEmail())
                .build();


        //기존 가입자 인지 쳌크
//        User originUser = userDetailService.loadUserByUsername()
//
//        if(originUser == null){
//            kakaoService.createNew(kakaoU)
//        }

        return response2.getBody();
    }
}
