package com.tongji.web.webchatbackend.controller;

import com.tongji.web.webchatbackend.entity.GroupMessage;
import com.tongji.web.webchatbackend.service.GroupMessageService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * (GroupMessage)表控制层
 *
 * @author makejava
 * @since 2023-12-29 21:21:40
 */
@RestController
@RequestMapping("groupMessage")
@CrossOrigin
public class GroupMessageController {
	/**
	 * 服务对象
	 */
	@Resource
	private GroupMessageService groupMessageService;

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	@GetMapping
	public ResponseEntity<List<GroupMessage>> queryByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
		if (page < 0 || size < 0)
			return ResponseEntity.badRequest().build();
		List<GroupMessage> groupMessages = this.groupMessageService.queryByPage(page, size);
		if (groupMessages == null || groupMessages.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(groupMessages);
	}

	/**
	 * 查询所有数据
	 *
	 * @return 查询结果
	 */
	@GetMapping("all")
	public ResponseEntity<List<GroupMessage>> queryAll() {
		return ResponseEntity.ok(this.groupMessageService.queryAll());
	}

	/**
	 * 通过主键查询单条数据
	 *
	 * @param groupMsgId 主键
	 * @return 单条数据
	 */
	@GetMapping("{groupMsgId}")
	public ResponseEntity<GroupMessage> queryByGroupMsgId(@PathVariable("groupMsgId") Integer groupMsgId) {
		return ResponseEntity.ok(this.groupMessageService.queryByGroupMsgId(groupMsgId));
	}

	/**
	 * 新增数据
	 *
	 * @param groupMessage 实体
	 * @return 新增结果
	 */
	@PostMapping
	public ResponseEntity<GroupMessage> add(@RequestBody GroupMessage groupMessage) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		Date          updateTime      = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
		groupMessage.setGroupMsgTime(updateTime);
		return ResponseEntity.ok(this.groupMessageService.save(groupMessage));
	}

	/**
	 * 编辑数据
	 *
	 * @param groupMessage 实体
	 * @return 编辑结果
	 */
	@PutMapping
	public ResponseEntity<GroupMessage> edit(@RequestBody GroupMessage groupMessage) {
		if (!this.groupMessageService.existsByGroupMsgId(groupMessage.getGroupMsgId()))
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(this.groupMessageService.save(groupMessage));
	}

	/**
	 * 删除数据
	 *
	 * @param groupMsgId 主键
	 * @return 删除是否成功
	 */
	@DeleteMapping
	public ResponseEntity<Boolean> deleteByGroupMsgId(Integer groupMsgId) {
		if (!this.groupMessageService.existsByGroupMsgId(groupMsgId))
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(this.groupMessageService.deleteByGroupMsgId(groupMsgId));
	}

	/**
	 * 通过用户id查询数据
	 *
	 * @param groupId 群组id
	 * @return 单条数据
	 */
	@GetMapping("group")
	public ResponseEntity<List<GroupMessage>> queryByGroupId(Integer groupId) {
		return ResponseEntity.ok(this.groupMessageService.queryByGroupId(groupId));
	}
}
