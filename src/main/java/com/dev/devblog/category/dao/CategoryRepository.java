package com.dev.devblog.category.dao;

import com.dev.devblog.category.dto.CategoryDto;
import com.dev.devblog.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryDynamicRepository {

//	@Query(value = "SELECT c FROM Category c" +
//			" ORDER BY c.createDate DESC ")
//	Page<CategoryDto> findAllByPageable(Pageable pageable);

//	@Query(value = "SELECT c FROM Category c" +
//			" WHERE c.categoryStatus = :status" +
//			" ORDER BY c.createDate DESC ")
//	Page<CategoryDto> findAllByPageableAndStatus(Pageable pageable, String status);
}
