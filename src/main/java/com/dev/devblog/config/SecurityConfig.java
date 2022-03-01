package com.dev.devblog.config;


import com.dev.devblog.user.service.UserCUDService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }
//    private UserCUDService userCUDService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //인증 무시 설정
        web.ignoring().antMatchers("/css/**", "/js/**", "/static/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //권한설정
        http.authorizeRequests()
                .mvcMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .mvcMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .mvcMatchers("/**").permitAll();
        //로그인 설정
        http.formLogin()
                .loginPage("/login")    //커스텀 로그인 페이지 사용
                .defaultSuccessUrl("/") //로그인 성공시 이동 페이지
                .permitAll();
        //로그아웃 설정
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true); //세션 초기화
        http.exceptionHandling()
                .accessDeniedPage("/denied");

        http.sessionManagement()
                .maximumSessions(-1)        // 최대 접속수 (-1 : 무한대)
                .expiredUrl("/login")       // 세션 제한시 리다이렉트할 URL
                .sessionRegistry(sessionRegistry());

//        http.authorizeRequests().antMatchers("/kakao/callback").permitAll()
//                .anyRequest().authenticated(
//            .and()
//                .oauth2Login();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //로그인 처리를 하기 위한 buider설정
        //auth.userDetailsService(userCUDService).passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
    }


}
