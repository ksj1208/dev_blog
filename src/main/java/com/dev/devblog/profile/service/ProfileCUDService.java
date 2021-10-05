package com.dev.devblog.profile.service;

import com.dev.devblog.profile.dao.ProfileRepository;
import com.dev.devblog.profile.domain.Profiles;
import com.dev.devblog.profile.dto.ProfileSaveRequest;
import com.dev.devblog.user.UserRepository;
import com.dev.devblog.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProfileCUDService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Transactional
    public void profileSave(ProfileSaveRequest request, Long userCode) {
        User user = userRepository.findById(userCode).orElseThrow(
                () -> new NoSuchElementException("프로필 작성 회원을 찾을 수 없습니다.")
        );
        profileRepository.save(Profiles.of(request, user).toCreateEntity());
    }
}
