package com.dev.devblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/static/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()
                .mvcMatchers("/","/info","/user/**").permitAll()
                .mvcMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated();*/

        http.authorizeRequests()
                .mvcMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .mvcMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .mvcMatchers("/**").permitAll();

        http.formLogin()

                .defaultSuccessUrl("/")
                .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

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


}
