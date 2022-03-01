package com.dev.devblog.adminuser.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author :  Lee Hosung
 * @packageName : com.dev.devblog.adminuser.dto
 * @date : 2022-01-19
 * @description :
 * ===========================================================
 * DATE                  AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2022-01-19           Lee Hosung           최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminUserUpdateRequest {
    private Long userCode;
    private String authority;
}
