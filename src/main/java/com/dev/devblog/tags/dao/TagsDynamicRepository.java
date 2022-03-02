package com.dev.devblog.tags.dao;

import com.dev.devblog.tags.dto.TagsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TagsDynamicRepository {

	Page<TagsDto> findAllByStatus(Pageable pageable, String status);
}
