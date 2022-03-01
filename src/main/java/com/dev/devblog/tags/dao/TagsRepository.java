package com.dev.devblog.tags.dao;

import com.dev.devblog.tags.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<Tags, Long>, TagsDynamicRepository {

//	@Query(value = "SELECT t FROM Tags t" +
//			" ORDER BY t.createDate DESC ")
//	Page<Tags> findAllByPageable(Pageable pageable);
//
//	@Query(value = "SELECT t FROM Tags t" +
//			" WHERE t.tagStatus = :status" +
//			" ORDER BY t.createDate DESC ")
//	Page<Tags> findAllByPageableAndStatus(Pageable pageable, String status);
}
