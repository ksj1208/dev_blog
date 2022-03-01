package com.dev.devblog.profile.domain;

import com.dev.devblog.board.dto.BoardStatusType;
import com.dev.devblog.profile.dto.ProfileSaveRequest;
import com.dev.devblog.profile.entity.BlogProfile;
import com.dev.devblog.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Profiles {

    private final String content;
    private final String subject;
    private final User user;

    private Profiles(ProfileSaveRequest request, User user) {
        this.content = request.getContent();
        this.subject = request.getSubject();
        this.user = user;
    }

    public static Profiles of(ProfileSaveRequest request, User user) {
        return new Profiles(request, user);
    }

    public BlogProfile toCreateEntity() {
        return BlogProfile.builder()
                .user(this.user)
                .subject(this.subject)
                .content(this.content)
                .createDate(LocalDateTime.now())
                .status(BoardStatusType.INACTIVE.getValue())
                .build();
    }
}
