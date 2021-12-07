package com.dev.devblog.user.service;

import com.dev.devblog.user.CustomUserDetails;
import com.dev.devblog.user.dao.UserRepository;
import com.dev.devblog.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId);
        if(user == null){
            throw new UsernameNotFoundException(userId);
        }
        //권한체크 수정필요 (현재 하드코딩되어있음)
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUserId(user.getUserId());
        customUserDetails.setUserCode(user.getUserCode());
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setAuthorities(authorities);
        customUserDetails.setNickName(user.getNickName());
        customUserDetails.setEmail(user.getEmail());
        customUserDetails.setAccountPath(user.getAccountPath());

        return customUserDetails;

    }

    @Transactional
    public User createNew(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }


}
