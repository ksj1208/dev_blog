package com.dev.devblog.profile.service;

import com.dev.devblog.profile.dao.ProfileRepository;
import com.dev.devblog.profile.dto.ProfileListResponse;
import com.dev.devblog.profile.dto.ProfileResponse;
import com.dev.devblog.profile.entity.BlogProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class ProfileReadService {

    private final ProfileRepository profileRepository;

    public ProfileListResponse getList(Pageable pageable){
        return ProfileListResponse.from(profileRepository.findAllWithPageable(pageable));
    }

    public ProfileResponse getDetail(Long profileCode) {
        BlogProfile profile = profileRepository.findById(profileCode).orElseThrow(
                () -> new NoSuchElementException("해당 프로필이 존재하지 않습니다.")
        );
        return ProfileResponse.from(profile);
    }

}
