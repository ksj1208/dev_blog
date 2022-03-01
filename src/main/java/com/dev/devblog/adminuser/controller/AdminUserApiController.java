package com.dev.devblog.adminuser.controller;

import com.dev.devblog.adminuser.dto.AdminUserListResponse;
import com.dev.devblog.adminuser.dto.AdminUserUpdateRequest;
import com.dev.devblog.adminuser.service.AdminUserCUDService;
import com.dev.devblog.adminuser.service.AdminUserReadService;
import com.dev.devblog.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminUserApiController {

    private final AdminUserReadService adminUserReadService;
    private final AdminUserCUDService adminUserCUDService;

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
    @PatchMapping("/admin/adminUser/authority")
    public ResponseEntity<String> adminUserUpdate(@RequestBody final AdminUserUpdateRequest request){
        log.info("================= 유저 권한 변경 시작 =================");
        log.info("Request Value : {} :: {}", request.getUserCode(), request.getAuthority());
        adminUserCUDService.adminUserUpdate(request);
        log.info("================= 유저 권한 변경 끝 =================");
        log.info("");
        log.info("");
        log.info("");

        return ResponseEntity.ok("변경이 완료되었습니다.");
    }
}
