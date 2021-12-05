package com.dev.devblog.profile.controller;

import com.dev.devblog.board.dto.BoardSaveRequest;
import com.dev.devblog.board.dto.BoardUpdateStatusRequest;
import com.dev.devblog.profile.dto.ProfileSaveRequest;
import com.dev.devblog.profile.dto.ProfileListResponse;
import com.dev.devblog.profile.dto.ProfileUpdateRequest;
import com.dev.devblog.profile.dto.ProfileUpdateStatusRequest;
import com.dev.devblog.profile.service.ProfileCUDService;
import com.dev.devblog.profile.service.ProfileReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProfileApiController {

    private final ProfileReadService profileReadService;
    private final ProfileCUDService profileCUDService;

    @GetMapping("/profiles/list")
    public ResponseEntity<ProfileListResponse> getList(final Pageable pageable){
        // 예) /profiles/list?page=0&size=20&sort=id,desc&sort=username,desc
        log.info("================= 프로필 리스트 시작 =================");
        ProfileListResponse response = profileReadService.getList(pageable);
        log.info(response.toString());
        log.info("================= 프로필 리스트 끝 =================");
        log.info("");
        log.info("");
        log.info("");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/profile/save")
    public ResponseEntity<String> profileCreate(@RequestBody final ProfileSaveRequest request) {
        log.info("================= 프로필 등록 시작 =================");
        profileCUDService.profileSave(request, 13L);
        log.info("================= 프로필 등록 끝 =================");
        log.info("");
        log.info("");
        log.info("");

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/profile/status")
    public ResponseEntity<String> profileUpdateStatus(@RequestBody final ProfileUpdateStatusRequest request) {
        log.info("================= 프로필 상태 변경 시작 =================");
        log.info("Request Value :" + request.getProfileCode() + " :: " + request.getStatus());
        profileCUDService.profileStatusUpdate(request);
        log.info("================= 프로필 상태 변경 끝 =================");
        log.info("");
        log.info("");
        log.info("");

        return ResponseEntity.ok("변경이 완료되었습니다.");
    }

    @PatchMapping("/profile/modify")
    public ResponseEntity<String> profileUpdate(@RequestBody final ProfileUpdateRequest request) {
        log.info("================= 프로필 변경 시작 =================");
        log.info("Request Value :" + request.getProfileCode() + " :: " + request.getSubject() + " :: " + request.getContent());
        profileCUDService.profileUpdate(request);
        log.info("================= 프로필 변경 끝 =================");
        log.info("");
        log.info("");
        log.info("");

        return ResponseEntity.ok("변경이 완료되었습니다.");
    }

}
