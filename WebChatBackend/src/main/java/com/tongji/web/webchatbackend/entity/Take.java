package com.tongji.web.webchatbackend.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (Take)实体类
 *
 * @author makejava
 * @since 2023-12-29 22:26:41
 */
@Data
@Entity
@Table(name = "take")
public class Take implements Serializable {
	@Serial
	private static final long serialVersionUID = 740103074931203983L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 非自增值请删去此行
	@Column(name = "id")
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "group_id")
	private Integer groupId;

	@Column(name = "role")
	private String role;

	@Column(name = "add_time")
	private Date addTime;
}
