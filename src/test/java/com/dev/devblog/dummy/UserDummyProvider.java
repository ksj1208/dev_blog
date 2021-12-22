package com.dev.devblog.dummy;

import com.dev.devblog.user.dao.UserRepository;
import com.dev.devblog.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserDummyProvider {

    @Autowired
    UserRepository userRepository;

    public User save() {
        User user = User.builder()
                .userId("test99")
                .password(new BCryptPasswordEncoder().encode("test!@"))
                .createDate(LocalDateTime.now())
                .build();

        return userRepository.save(user);

    }

    public User createUserDummy() {
        User user = User.builder()
                .userId("test0124")
                .password(new BCryptPasswordEncoder().encode("0124"))
                .createDate(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }
}
