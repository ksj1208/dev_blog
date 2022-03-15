package com.dev.devblog.adminuser.service;


import com.dev.devblog.adminuser.dao.AdminUserRepository;
import com.dev.devblog.adminuser.dto.AdminUserListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminUserReadService {

    private final AdminUserRepository adminUserRepository;

    public AdminUserListResponse getList(Pageable pageable){
        return AdminUserListResponse.from(adminUserRepository.findAllWithPageable(pageable));
    }


}
