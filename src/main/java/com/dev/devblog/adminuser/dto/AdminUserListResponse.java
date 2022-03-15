package com.dev.devblog.adminuser.dto;

import com.dev.devblog.user.entity.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;


/**
 * @author :  Lee Hosung
 * @packageName : com.dev.devblog.adminuser.dto
 * @date : 2022-01-13
 * @description :
 * ===========================================================
 * DATE                  AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2022-01-13           Lee Hosung           최초 생성
 */
@Getter
@Slf4j
public class AdminUserListResponse {

    private final Page<AdminUserResponse> adminUserList;
    private AdminUserListResponse(Page<User> list){

        this.adminUserList = list.map(AdminUserResponse::from);
        log.info(adminUserList.toString());
    }

    public static AdminUserListResponse from(Page<User> list){
        return new AdminUserListResponse(list);
    }
}
