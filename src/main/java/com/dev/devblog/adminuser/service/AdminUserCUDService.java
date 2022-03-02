package com.dev.devblog.adminuser.service;

import com.dev.devblog.adminuser.dao.AdminUserRepository;
import com.dev.devblog.adminuser.dto.AdminUserUpdateRequest;
import com.dev.devblog.user.CustomUserDetails;
import com.dev.devblog.user.domain.Users;
import com.dev.devblog.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


/**
 * @author :  Lee Hosung
 * @packageName : com.dev.devblog.adminuser.service
 * @date : 2022-01-19
 * @description :
 * ===========================================================
 * DATE                  AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2022-01-19           Lee Hosung           최초 생성
 */


@Service
@RequiredArgsConstructor
@Transactional
public class AdminUserCUDService {

    private final SessionRegistry sessionRegistry;
    private final AdminUserRepository adminUserRepository;

    public void adminUserUpdate(AdminUserUpdateRequest request) {

        User user = adminUserRepository.findById(request.getUserCode()).orElseThrow(
                () -> new NoSuchElementException("해당 유저가 존재하지 않습니다.")
        );
        // 권한 변경된 계정 세션 만료
        List<CustomUserDetails> users = sessionRegistry.getAllPrincipals().stream()
                .map(o -> (CustomUserDetails) o).collect(Collectors.toList());

        for(CustomUserDetails userDetails : users){
            if(userDetails.getUserCode() == request.getUserCode()){
                List<SessionInformation> sessions = sessionRegistry.getAllSessions(userDetails, false);
                sessions.forEach(SessionInformation::expireNow);
            }
        }


        user.updateAuthority(request.getAuthority());
    }
}
