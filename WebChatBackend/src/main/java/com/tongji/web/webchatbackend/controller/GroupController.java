package com.tongji.web.webchatbackend.controller;

import com.tongji.web.webchatbackend.entity.Group;
import com.tongji.web.webchatbackend.service.GroupService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (Group)表控制层
 *
 * @author makejava
 * @since 2023-12-29 21:08:13
 */
@RestController
@RequestMapping("group")
@CrossOrigin
public class GroupController {
	/**
	 * 服务对象
	 */
	@Resource
	private GroupService groupService;

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	@GetMapping
	public ResponseEntity<List<Group>> queryByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
		if (page < 0 || size < 0)
			return ResponseEntity.badRequest().build();
		List<Group> groups = this.groupService.queryByPage(page, size);
		if (groups == null || groups.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(groups);
	}

	/**
	 * 查询所有数据
	 *
	 * @return 查询结果
	 */
	@GetMapping("all")
	public ResponseEntity<List<Group>> queryAll() {
		return ResponseEntity.ok(this.groupService.queryAll());
	}

	/**
	 * 通过主键查询单条数据
	 *
	 * @param groupId 主键
	 * @return 单条数据
	 */
	@GetMapping("{groupId}")
	public ResponseEntity<Group> queryByGroupId(@PathVariable("groupId") Integer groupId) {
		return ResponseEntity.ok(this.groupService.queryByGroupId(groupId));
	}

	/**
	 * 新增数据
	 *
	 * @param group 实体
	 * @return 新增结果
	 */
	@PostMapping
	public ResponseEntity<Group> add(@RequestBody Group group) {
		if (this.groupService.existsByGroupId(group.getGroupId()))
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(this.groupService.save(group));
	}

	/**
	 * 编辑数据
	 *
	 * @param group 实体
	 * @return 编辑结果
	 */
	@PutMapping
	public ResponseEntity<Group> edit(@RequestBody Group group) {
		if (!this.groupService.existsByGroupId(group.getGroupId()))
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(this.groupService.save(group));
	}

	/**
	 * 删除数据
	 *
	 * @param groupId 主键
	 * @return 删除是否成功
	 */
	@DeleteMapping
	public ResponseEntity<Boolean> deleteByGroupId(Integer groupId) {
		if (!this.groupService.existsByGroupId(groupId))
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(this.groupService.deleteByGroupId(groupId));
	}
}
