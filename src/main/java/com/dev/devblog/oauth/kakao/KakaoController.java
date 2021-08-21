package com.dev.devblog.oauth.kakao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Slf4j
public class KakaoController {
    KakaoAPI kakaoApi = new KakaoAPI();

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam("code") String code, HttpSession session){
        System.out.println("login~~~~~~~~~~~~~~~``");
        ModelAndView mav = new ModelAndView();
        //인증코드 요청 전달
        String accessToken = kakaoApi.getAccessToken(code);
        //인증코드로 토큰 전달
        HashMap<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);

        log.debug("login info : " + userInfo.toString());

        if(userInfo.get("email") != null){
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("accessToken", accessToken);
        }

        mav.addObject("userId", userInfo.get("email"));
        mav.setViewName("/");
        return mav;

    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session){
        ModelAndView mav = new ModelAndView();

        kakaoApi.kakaoLogout((String)session.getAttribute("accessToken"));
        session.removeAttribute("accessToken");
        session.removeAttribute("userId");
        mav.setViewName("/");
        return mav;
    }
}
