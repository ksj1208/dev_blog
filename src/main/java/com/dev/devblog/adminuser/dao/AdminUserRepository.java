package com.dev.devblog.adminuser.dao;

import com.dev.devblog.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author :  Lee Hosung
 * @packageName : com.dev.devblog.adminuser.dao
 * @date : 2022-01-13
 * @description :
 * ===========================================================
 * DATE                  AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2022-01-13           Lee Hosung           최초 생성
 */
public interface AdminUserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT b FROM User b" +
            " ORDER BY b.createDate DESC ")
    Page<User> findAllWithPageable(Pageable pageable);


}
