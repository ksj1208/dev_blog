package com.dev.devblog.category.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "CATEGORY")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@ToString
public class Category {

	@Id
	@Column(name = "CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryId;

	@Column(name = "CATEGORY_NAME")
	private String categoryName;

	@Column(name = "CATEGORY_STATUS")
	private String categoryStatus;

	@Column(name = "CREATE_DATE")
	private LocalDateTime createDate;

	@Column(name = "UPDATE_DATE")
	private LocalDateTime updateDate;



	public void updateCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public void updateCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void updateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
}