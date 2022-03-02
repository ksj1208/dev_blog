package com.dev.devblog.profile.dto;

import com.dev.devblog.profile.entity.BlogProfile;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class ProfileResponse {
    private final Long profileCode;
    private final String subject;
    private final String content;
    private final String status;
    private final LocalDateTime createDate;
    private final String writer;

    private ProfileResponse(BlogProfile profile) {
        this.profileCode = profile.getProfileCode();
        this.content = profile.getContent();
        this.subject = profile.getSubject();
        this.status = profile.getStatus();
        this.createDate = profile.getCreateDate();
        this.writer = profile.getUser().getNickName();
    }

    public static ProfileResponse from(BlogProfile profile) {
        return new ProfileResponse(profile);
    }

}
