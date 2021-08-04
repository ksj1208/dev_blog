package com.dev.devblog.user;

import com.dev.devblog.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/user/{role}/{userId}/{password}")
    public User createUser(@ModelAttribute User user){
        return userService.createNew(user);
    }
}
