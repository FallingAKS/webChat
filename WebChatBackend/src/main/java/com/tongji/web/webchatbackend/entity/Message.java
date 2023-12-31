package com.tongji.web.webchatbackend.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (Message)实体类
 *
 * @author makejava
 * @since 2023-12-29 22:26:48
 */
@Data
@Entity
@Table(name = "message")
public class Message implements Serializable {
	@Serial
	private static final long serialVersionUID = 552234016948560358L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 非自增值请删去此行
	@Column(name = "msg_id")
	private Integer msgId;

	@Column(name = "msg_content")
	private String msgContent;

	@Column(name = "msg_from")
	private Integer msgFrom;

	@Column(name = "msg_to")
	private Integer msgTo;

	@Column(name = "msg_time")
	private Date msgTime;

	@Column(name = "file_type")
	private String fileType;

	@Column(name = "file_path")
	private String filePath;
}
