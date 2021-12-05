package com.dev.devblog.profile.service;

import com.dev.devblog.board.dto.BoardUpdateStatusRequest;
import com.dev.devblog.board.entity.Board;
import com.dev.devblog.profile.dao.ProfileRepository;
import com.dev.devblog.profile.domain.Profiles;
import com.dev.devblog.profile.dto.ProfileSaveRequest;
import com.dev.devblog.profile.dto.ProfileUpdateRequest;
import com.dev.devblog.profile.dto.ProfileUpdateStatusRequest;
import com.dev.devblog.profile.entity.BlogProfile;
import com.dev.devblog.user.UserRepository;
import com.dev.devblog.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileCUDService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;


    public void profileSave(ProfileSaveRequest request, Long userCode) {
        User user = userRepository.findById(userCode).orElseThrow(
                () -> new NoSuchElementException("프로필 작성 회원을 찾을 수 없습니다.")
        );
        profileRepository.save(Profiles.of(request, user).toCreateEntity());
    }

    public void profileStatusUpdate(ProfileUpdateStatusRequest request) {
        BlogProfile profile = profileRepository.findById(request.getProfileCode()).orElseThrow(
                () -> new NoSuchElementException("변경할 프로필을 찾을 수 없습니다")
        );
        int result = profileRepository.updateToDisableAllProfileCode();
        profile.updateStatus(request.getStatus());
    }

    public void profileUpdate(ProfileUpdateRequest request) {
        BlogProfile profile = profileRepository.findById(request.getProfileCode()).orElseThrow(
                () -> new NoSuchElementException("수정할 게시글이 존재하지 않습니다.")
        );
        profile.updateSubject(request.getSubject());
        profile.updateContent(request.getContent());
        profile.updateDate(LocalDateTime.now());
    }
}
