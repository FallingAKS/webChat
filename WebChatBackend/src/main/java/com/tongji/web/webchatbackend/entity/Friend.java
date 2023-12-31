package com.tongji.web.webchatbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (Friend)实体类
 *
 * @author makejava
 * @since 2023-12-29 22:27:06
 */
@Data
@Entity
@Table(name = "friend")
public class Friend implements Serializable {
	@Serial
	private static final long serialVersionUID = -80110660882873497L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 非自增值请删去此行
	@Column(name = "id")
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "friend_id")
	private Integer friendId;
}
