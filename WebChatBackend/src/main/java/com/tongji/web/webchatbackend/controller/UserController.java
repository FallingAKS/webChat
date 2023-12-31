package com.tongji.web.webchatbackend.controller;

import com.tongji.web.webchatbackend.entity.User;
import com.tongji.web.webchatbackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2023-12-29 20:19:22
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
	/**
	 * 服务对象
	 */
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
	public ResponseEntity<List<User>> queryByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
		if (page < 0 || size < 0)
			return ResponseEntity.badRequest().build();
		List<User> users = this.userService.queryByPage(page, size);
		if (users == null || users.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(users);
	}

	/**
	 * 查询所有数据
	 *
	 * @return 查询结果
	 */
	@GetMapping("all")
	public ResponseEntity<List<User>> queryAll() {
		return ResponseEntity.ok(this.userService.queryAll());
	}

	/**
	 * 通过主键查询单条数据
	 *
	 * @param userId 主键
	 * @return 单条数据
	 */
	@GetMapping("{userId}")
	public ResponseEntity<User> queryByUserId(@PathVariable("userId") Integer userId) {
		if (!this.userService.existsByUserId(userId))
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(this.userService.queryByUserId(userId));
	}

	/**
	 * 新增数据
	 *
	 * @param user 实体
	 * @return 新增结果
	 */
	@PostMapping
	public ResponseEntity<User> add(@RequestBody User user) {
		if (this.userService.existsByUserId(user.getId()))
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(this.userService.save(user));
	}

	/**
	 * 编辑数据
	 *
	 * @param user 实体
	 * @return 编辑结果
	 */
	@PutMapping
	public ResponseEntity<User> edit(@RequestBody User user) {
		if (!this.userService.existsByUserId(user.getId()))
			return ResponseEntity.notFound().build();
		if(this.userService.existsByName(user.getName())){
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(this.userService.save(user));
	}

	/**
	 * 删除数据
	 *
	 * @param userId 主键
	 * @return 删除是否成功
	 */
	@DeleteMapping
	public ResponseEntity<Boolean> deleteByUserId(Integer userId) {
		if (!this.userService.existsByUserId(userId))
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(this.userService.deleteByUserId(userId));
	}


	@PostMapping("login")
	public ResponseEntity<User> login(@RequestBody User user) {
		if (this.userService.existsByNameAndPassword(user.getName(), user.getPassword()))
			return ResponseEntity.ok(this.userService.queryByName(user.getName()));
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@PostMapping("signup")
	public ResponseEntity<User> signup(@RequestBody User user) {
		if (this.userService.existsByName(user.getName()))
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		return ResponseEntity.ok(this.userService.save(user));
	}
}
