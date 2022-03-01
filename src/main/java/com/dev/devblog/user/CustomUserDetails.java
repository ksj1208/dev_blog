package com.dev.devblog.user;

import com.dev.devblog.user.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Data
public class CustomUserDetails implements UserDetails {

    private long userCode;
    private String userId;
    private String password;
    private String nickName;
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime passwordUpdateDate;
    private String email;
    private String accountPath;
    private String authorities;


    //해당 유저 권한 목록
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(authorities));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    /*
    * 계정 만료 여부
    * true : 만료 안됨
    * false : 만료됨
    * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*
     * 계정 잠김 여부
     * true : 잠기지 않음
     * false : 잠김
     * */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /*
    * 비밀번호 만료 여부
    * true : 만료 안됨
    * false : 만료됨
    * */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*
    * 사용자 활성화 여부
    * true : 활성화
    * false : 비활성화*/
    @Override
    public boolean isEnabled() {
        return true;
    }
    //이메일인증여부 추후 보완필요

    //user pk값
    public long getUserCode(){
        return userCode;
    }

    //user nickname
    public String getNickName() {return nickName; }



    protected CustomUserDetails(User user){
        this.userId = user.getUserId();
        this.password = user.getPassword();
        //this.authorities = authorities();
        this.nickName = user.getNickName();
        this.email = user.getEmail();
        this.accountPath = user.getAccountPath();
    }

    public static CustomUserDetails from(User user){
        return new CustomUserDetails(user);
    }



}













