package com.tongji.web.webchatbackend.controller;

import com.tongji.web.webchatbackend.entity.Message;
import com.tongji.web.webchatbackend.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * (Message)表控制层
 *
 * @author makejava
 * @since 2023-12-29 21:10:21
 */
@RestController
@RequestMapping("message")
@CrossOrigin
public class MessageController {
	/**
	 * 服务对象
	 */
	@Resource
	private MessageService messageService;

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	@GetMapping
	public ResponseEntity<List<Message>> queryByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
		if (page < 0 || size < 0)
			return ResponseEntity.badRequest().build();
		List<Message> messages = this.messageService.queryByPage(page, size);
		if (messages == null || messages.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(messages);
	}

	/**
	 * 查询所有数据
	 *
	 * @return 查询结果
	 */
	@GetMapping("all")
	public ResponseEntity<List<Message>> queryAll() {
		return ResponseEntity.ok(this.messageService.queryAll());
	}

	/**
	 * 通过主键查询单条数据
	 *
	 * @param msgId 主键
	 * @return 单条数据
	 */
	@GetMapping("{msgId}")
	public ResponseEntity<Message> queryByMsgId(@PathVariable("msgId") Integer msgId) {
		return ResponseEntity.ok(this.messageService.queryByMsgId(msgId));
	}

	/**
	 * 新增数据
	 *
	 * @param message 实体
	 * @return 新增结果
	 */
	@PostMapping
	public ResponseEntity<Message> add(@RequestBody Message message) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		Date          updateTime      = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
		message.setMsgTime(updateTime);
		return ResponseEntity.ok(this.messageService.save(message));
	}

	/**
	 * 编辑数据
	 *
	 * @param message 实体
	 * @return 编辑结果
	 */
	@PutMapping
	public ResponseEntity<Message> edit(@RequestBody Message message) {
		if (!this.messageService.existsByMsgId(message.getMsgId()))
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(this.messageService.save(message));
	}

	/**
	 * 删除数据
	 *
	 * @param msgId 主键
	 * @return 删除是否成功
	 */
	@DeleteMapping
	public ResponseEntity<Boolean> deleteByMsgId(Integer msgId) {
		if (!this.messageService.existsByMsgId(msgId))
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(this.messageService.deleteByMsgId(msgId));
	}

	/**
	 * 通过用户id查询数据
	 *
	 * @param userId 用户id
	 * @param friendId 好友id
	 * @return 单条数据
	 */
	@GetMapping("user")
	public ResponseEntity<List<Message>> queryByUserId(Integer userId, Integer friendId) {
		return ResponseEntity.ok(this.messageService.queryByUserIdAndFriendId(userId, friendId));
	}
}
