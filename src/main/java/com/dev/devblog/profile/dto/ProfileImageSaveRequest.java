package com.dev.devblog.profile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ProfileImageSaveRequest {
    private String content;
    private String subject;

}
