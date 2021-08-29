package com.dev.devblog;

import com.dev.devblog.annotation.ControllerTargetTest;
import com.dev.devblog.dummy.UserDummyProvider;
import com.dev.devblog.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ControllerTargetTest
public class SecurityTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserDummyProvider userDummyProvider;

    private User user;

    @BeforeEach
    void setup() {
        //TODO 테스트 코드를 실행 전 미리 동작하는 부분 (DB에 기본정보를 넣고 시작해야하는 경우)
        //외부라이브러리 사용이나 보안적으로 존재하는 필수데이터
        user = userDummyProvider.save();
    }

    @DisplayName("security 로그인 인증 테스트 - 로그인 성공 프로세스 테스트")
    @Test
    public void securityLoginSuccessTest() throws Exception {
        //form 전송
        mockMvc.perform(post("/login")
                .param("username", user.getUserId())
                .param("password", "test!@")
                .with(csrf()))
                .andExpect(status().isOk());
    }
}
