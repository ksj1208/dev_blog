package com.dev.devblog.oauth.kakao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "kakao-info")
public class KakaoConfig {

}
