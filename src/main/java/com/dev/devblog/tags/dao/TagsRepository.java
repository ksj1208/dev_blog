package com.dev.devblog.tags.dao;

import com.dev.devblog.tags.entity.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagsRepository extends JpaRepository<Tags, Long> {

	@Query(value = "SELECT c FROM Tags c" +
			" ORDER BY c.createDate DESC ")
	Page<Tags> findAllByPageable(Pageable pageable);
}
