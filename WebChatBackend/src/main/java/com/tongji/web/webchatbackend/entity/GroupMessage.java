package com.tongji.web.webchatbackend.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (GroupMessage)实体类
 *
 * @author makejava
 * @since 2023-12-29 22:26:54
 */
@Data
@Entity
@Table(name = "group_message")
public class GroupMessage implements Serializable {
	@Serial
	private static final long serialVersionUID = 384846015006758727L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 非自增值请删去此行
	@Column(name = "group_msg_id")
	private Integer groupMsgId;

	@Column(name = "group_msg_content")
	private String groupMsgContent;

	@Column(name = "group_msg_from")
	private Integer groupMsgFrom;

	@Column(name = "group_id")
	private Integer groupId;

	@Column(name = "group_msg_time")
	private Date groupMsgTime;

	@Column(name = "group_file_type")
	private String groupFileType;

	@Column(name = "group_file_path")
	private String groupFilePath;
}
