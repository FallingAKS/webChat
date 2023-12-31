package com.tongji.web.webchatbackend.controller;

import com.tongji.web.webchatbackend.entity.Friend;
import com.tongji.web.webchatbackend.service.FriendService;
import com.tongji.web.webchatbackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (Friend)表控制层
 *
 * @author makejava
 * @since 2023-12-29 20:39:02
 */
@RestController
@RequestMapping("friend")
@CrossOrigin
public class FriendController {
	/**
	 * 服务对象
	 */
	@Resource
	private FriendService friendService;

	@Resource
	private UserService userService;

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	@GetMapping
	public ResponseEntity<List<Friend>> queryByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
		if (page < 0 || size < 0)
			return ResponseEntity.badRequest().build();
		List<Friend> friends = this.friendService.queryByPage(page, size);
		if (friends == null || friends.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(friends);
	}

	/**
	 * 查询所有数据
	 *
	 * @return 查询结果
	 */
	@GetMapping("all")
	public ResponseEntity<List<Friend>> queryAll() {
		return ResponseEntity.ok(this.friendService.queryAll());
	}

	/**
	 * 通过主键查询单条数据
	 *
	 * @param id 主键
	 * @return 单条数据
	 */
	@GetMapping("{id}")
	public ResponseEntity<Friend> queryById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(this.friendService.queryById(id));
	}

	/**
	 * 新增数据
	 *
	 * @param userId 用户id
	 * @param friendName 好友名
	 * @return 新增结果
	 */
	@PostMapping
	public ResponseEntity<Friend> add(Integer userId, String friendName) {
		if (!this.userService.existsByName(friendName)) {
			return ResponseEntity.notFound().build();
		}
		Friend friend = new Friend();
		Integer friendId = this.userService.queryByName(friendName).getId();
		if (userId.equals(friendId))
			return ResponseEntity.badRequest().build();

		if (this.friendService.existsByUserIdAndFriendId(userId, friendId) ||
				this.friendService.existsByUserIdAndFriendId(friendId, userId))
			return ResponseEntity.badRequest().build();
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		return ResponseEntity.ok(this.friendService.save(friend));
	}

	/**
	 * 编辑数据
	 *
	 * @param friend 实体
	 * @return 编辑结果
	 */
	@PutMapping
	public ResponseEntity<Friend> edit(@RequestBody Friend friend) {
		if (!this.friendService.existsById(friend.getId()))
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(this.friendService.save(friend));
	}

	/**
	 * 删除数据
	 *
	 * @param userId 用户id
	 * @param friendId 好友id
	 * @return 删除是否成功
	 */
	@DeleteMapping
	public ResponseEntity<Boolean> delete(Integer userId, Integer friendId) {
		if (this.friendService.existsByUserIdAndFriendId(userId, friendId)) {
			return ResponseEntity.ok(this.friendService.deleteByUserIdAndFriendId(userId, friendId));
		} else if (this.friendService.existsByUserIdAndFriendId(friendId, userId)) {
			return ResponseEntity.ok(this.friendService.deleteByUserIdAndFriendId(friendId, userId));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * 通过用户id查询所有好友
	 *
	 * @param id 用户id
	 * @return 查询结果
	 */
	@GetMapping("user/{id}")
	public ResponseEntity<List<Integer>> queryByUserId(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(this.friendService.queryByUserId(id));
	}
}
