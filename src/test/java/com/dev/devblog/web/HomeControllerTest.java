package com.dev.devblog.web;

import com.dev.devblog.annotation.ControllerTargetTest;
import com.dev.devblog.dummy.UserDummyProvider;
import com.dev.devblog.user.UserDetailServiceImpl;
import com.dev.devblog.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ControllerTargetTest
public class HomeControllerTest {

    @Autowired
    UserDummyProvider userDummyProvider;
    @Autowired
    ObjectMapper objectMapper;

    private User user;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        user = userDummyProvider.createUserDummy();
    }

    @DisplayName("HomeController 테스트 - 메인 페이지")
    @Test
    public void homeViewTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @DisplayName("HomeController 테스트 - 로그인 성공 프로세스 테스트")
    @Test
    public void loginViewSuccessTest() throws Exception {
        mockMvc.perform(post("/login")
                .param("username", "test0124")
                .param("password", "0124")
                .with(csrf()))
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("HomeController 테스트 - 로그인 실패 프로세스 테스트")
    @Test
    public void loginViewFailTest() throws Exception {
        mockMvc.perform(post("/login")
                .param("username", user.getUserId())
                .param("password", "123123")
                .with(csrf()))
                .andExpect(unauthenticated())
                .andExpect(redirectedUrl("/login?error"));
    }

    @DisplayName("HomeController 테스트 - 회원가입 성공 프로세스 테스트")
    @Test
    public void signupSuccessTest() throws Exception {

        mockMvc.perform(post("/signup")
                        .param("userId", "test")
                        .param("password", "0124")
                        .param("nickName", "hstar0124")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andDo(print());

    }

    @DisplayName("HomeController 테스트 - 어드민페이지 일반유저 접근 테스트")
    @Test
    public void adminPageWithUserTest() throws Exception {
        mockMvc.perform(get("/admin")
                .with(user("test01")
                        .password("0124")
                        .roles("USER")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/denied"))
                .andDo(print());
    }

    @DisplayName("HomeController 테스트 - 어드민페이지 어드민유저 접근 테스트")
    @Test
    public void adminPageWithAdminTest() throws Exception {
        mockMvc.perform(get("/admin")
                .with(user("admin")
                        .password("0124")
                        .roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/admin"))
                .andDo(print());
    }

    @DisplayName("HomeController 테스트 - 유저페이지 어드민유저 접근 테스트")
    @Test
    public void userPageWithAdminTest() throws Exception {
        mockMvc.perform(get("/member/info")
                .with(user("admin")
                        .password("0124")
                        .roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/user_info"))
                .andDo(print());
    }

    @DisplayName("HomeController 테스트 - 유저페이지 유저 접근 테스트")
    @Test
    public void userPageWithUserTest() throws Exception {
        mockMvc.perform(get("/member/info")
                .with(user("user")
                        .password("0124")
                        .roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/user_info"))
                .andDo(print());
    }

}
