package com.dev.devblog.user.login.dao;

import com.dev.devblog.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<User, Long> {
}
