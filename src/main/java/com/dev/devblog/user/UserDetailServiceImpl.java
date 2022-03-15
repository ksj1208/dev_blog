package com.dev.devblog.user;

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
import java.util.NoSuchElementException;


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
//        authorities.add(new SimpleGrantedAuthority(user.getAuthority()));
//        CustomUserDetails customUserDetails = new CustomUserDetails(user);
//        customUserDetails.setUserId(user.getUserId());
//        customUserDetails.setUserCode(user.getUserCode());
//        customUserDetails.setPassword(user.getPassword());
//        customUserDetails.setAuthorities(user.getAuthority());
//        customUserDetails.setNickName(user.getNickName());
//        customUserDetails.setEmail(user.getEmail());
//        customUserDetails.setAccountPath(user.getAccountPath());
        return CustomUserDetails.from(user);
//        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), authorities);
    }

    @Transactional
    public User createNew(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }


}
