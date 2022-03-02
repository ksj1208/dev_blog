package com.dev.devblog.oauth.kakao;

import com.dev.devblog.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface KakaoRepository extends JpaRepository<User,Long> {
    User findByUserId(String userId);

}
