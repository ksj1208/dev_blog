package com.dev.devblog.category.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "CATEGORY")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}