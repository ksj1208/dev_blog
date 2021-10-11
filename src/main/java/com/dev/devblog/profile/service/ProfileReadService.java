package com.dev.devblog.profile.service;

import com.dev.devblog.profile.dao.ProfileRepository;
import com.dev.devblog.profile.dto.ProfileListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileReadService {

    private final ProfileRepository profileRepository;

    public ProfileListResponse getList(Pageable pageable){
        return ProfileListResponse.from(profileRepository.findAllByPageable(pageable));
    }

}
