package com.tongji.web.webchatbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2023-12-29 20:22:46
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
	@Serial
	private static final long serialVersionUID = 806057829458308440L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "gender")
	private Integer gender;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "password")
	private String password;

	@Column(name = "signature")
	private String signature;
}
