package com.dev.devblog.tags.service;


import com.dev.devblog.tags.dao.TagsRepository;
import com.dev.devblog.tags.dto.TagsListResponse;
import com.dev.devblog.tags.dto.TagsResponse;
import com.dev.devblog.tags.entity.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TagsReadService {

	private final TagsRepository tagsRepository;

	public TagsListResponse getList(Pageable pageable){
		return TagsListResponse.from(tagsRepository.findAllByPageable(pageable));
	}

	public TagsResponse getDetail(Long tagId) {
		Tags tags = tagsRepository.findById(tagId).orElseThrow(
				() -> new NoSuchElementException("해당 태그가 존재하지 않습니다.")
		);
		return TagsResponse.from(tags);
	}
}
