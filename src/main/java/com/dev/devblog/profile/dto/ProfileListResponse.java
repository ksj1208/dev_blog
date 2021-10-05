package com.dev.devblog.profile.dto;


import com.dev.devblog.profile.entity.BlogProfile;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

@Getter
@Slf4j
public class ProfileListResponse {

    private final Page<ProfileResponse> profileList;

    private ProfileListResponse(Page<BlogProfile> list) {
        this.profileList = list.map(ProfileResponse::from);
        log.info(profileList.toString());
    }

    public static ProfileListResponse from(Page<BlogProfile> list) {
        return new ProfileListResponse(list);
    }
}
