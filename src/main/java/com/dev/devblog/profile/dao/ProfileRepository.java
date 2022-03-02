package com.dev.devblog.profile.dao;

import com.dev.devblog.profile.entity.BlogProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface ProfileRepository extends JpaRepository<BlogProfile, Long> {

    @Query(value = "SELECT b FROM BlogProfile b" +
            " ORDER BY b.createDate DESC ")
    Page<BlogProfile> findAllWithPageable(Pageable pageable);

    @Modifying
    @Query(value = "UPDATE BlogProfile set status = 'I'")
    int updateToDisableAllProfileCode();

}
