package com.tongji.web.webchatbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (Group)实体类
 *
 * @author makejava
 * @since 2023-12-31 13:22:06
 */
@Data
@Entity
@Table(name = "`group`")
public class Group implements Serializable {
	@Serial
	private static final long serialVersionUID = -38527220673491841L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 非自增值请删去此行
	@Column(name = "group_id")
	private Integer groupId;

	@Column(name = "group_name")
	private String groupName;

	@Column(name = "group_master")
	private Integer groupMaster;

	@Column(name = "group_avatar")
	private String groupAvatar;

	@Column(name = "group_create_time")
	private String groupCreateTime;

	@Column(name = "group_star_message")
	private String groupStarMessage;
}
