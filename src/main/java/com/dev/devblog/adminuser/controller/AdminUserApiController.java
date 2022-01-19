package com.dev.devblog.adminuser.controller;

import com.dev.devblog.adminuser.dto.AdminUserListResponse;
import com.dev.devblog.adminuser.service.AdminUserReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminUserApiController {


    private final AdminUserReadService adminUserReadService;
    //private final AdminUserCUDService adminUserCUDService;
    @GetMapping("/admin/adminUser/list")
    public ResponseEntity<AdminUserListResponse> getList(final Pageable pageable){
        log.info("================= 어드민 유저 리스트 시작 =================");
        AdminUserListResponse response = adminUserReadService.getList(pageable);
        log.info(response.toString());
        log.info("================= 어드민 유저 리스트 끝 =================");
        log.info("");
        log.info("");
        log.info("");
        return ResponseEntity.ok(response);
    }
}
