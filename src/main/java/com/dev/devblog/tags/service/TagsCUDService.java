package com.dev.devblog.tags.service;



import com.dev.devblog.tags.dao.TagsRepository;
import com.dev.devblog.tags.domain.TagDomain;
import com.dev.devblog.tags.dto.TagsSaveRequest;
import com.dev.devblog.tags.entity.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Transactional
public class TagsCUDService {

	private final TagsRepository tagRepository;

	public void tagSave(TagsSaveRequest request, Long userCode) {
		tagRepository.save(TagDomain.of(request).toCreateEntity());
	}

	public void tagDelete(List<Long> tagIdArr) {
		for(Long tagId : tagIdArr) {
			tagRepository.findById(tagId).orElseThrow(
					() -> new NoSuchElementException("삭제할 카테고리가 존재하지 않습니다.")
			);

			tagRepository.deleteById(tagId);
		}
	}

	public void tagUpdate(TagsSaveRequest request) {
		Tags tags = tagRepository.findById(request.getTagId()).orElseThrow(
				() -> new NoSuchElementException("수정할 카테고리가 존재하지 않습니다.")
		);

		tags.updateTagName(request.getTagName());
		tags.updateTagStatus(request.getTagStatus());
		tags.updateDate(LocalDateTime.now());
	}
}
