package com.dev.devblog.category.dao;

import com.dev.devblog.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(value = "SELECT c FROM Category c" +
			" ORDER BY c.createDate DESC ")
	Page<Category> findAllByPageable(Pageable pageable);
}
