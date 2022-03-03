package com.dev.devblog.category.dao;

import com.dev.devblog.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryDynamicRepository {

	@Query(value = "SELECT count(c.categoryId) FROM Category c WHERE c.categoryName = :categoryName")
	Long findByCategoryName(@Param("categoryName") String categoryName);

	@Query(value = "SELECT c FROM Category c " +
			" WHERE c.categoryStatus = :status")
	List<Category> findAllByStatus(@Param("status") String status);


//	@Query(value = "SELECT c FROM Category c" +
//			" ORDER BY c.createDate DESC ")
//	Page<CategoryDto> findAllByPageable(Pageable pageable);

//	@Query(value = "SELECT c FROM Category c" +
//			" WHERE c.categoryStatus = :status" +
//			" ORDER BY c.createDate DESC ")
//	Page<CategoryDto> findAllByPageableAndStatus(Pageable pageable, String status);
}
