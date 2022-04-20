package com.dev.devblog.tags.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Table(name = "TAGS")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@ToString
public class Tags {

	@Id
	@Column(name = "TAG_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tagId;

	@Column(name = "TAG_NAME")
	private String tagName;

	@Column(name = "TAG_STATUS")
	private String tagStatus;

	@Column(name = "CREATE_DATE")
	private LocalDateTime createDate;

	@Column(name = "UPDATE_DATE")
	private LocalDateTime updateDate;

	public static Tags toEntity(String key) {
		return Tags.builder()
				.tagName(key)
				.createDate(LocalDateTime.now())
				.updateDate(LocalDateTime.now())
				.tagStatus("사용")
				.build();
	}

	public void updateTagStatus(String tagStatus) {
		this.tagStatus = tagStatus;
	}

	public void updateTagName(String tagName) {
		this.tagName = tagName;
	}

	public void updateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
}