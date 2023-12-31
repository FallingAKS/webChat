package com.tongji.web.webchatbackend.controller;

import com.tongji.web.webchatbackend.entity.Take;
import com.tongji.web.webchatbackend.service.TakeService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (Take)表控制层
 *
 * @author makejava
 * @since 2023-12-29 20:59:04
 */
@RestController
@RequestMapping("take")
@CrossOrigin
public class TakeController {
	/**
	 * 服务对象
	 */
	@Resource
	private TakeService takeService;

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	@GetMapping
	public ResponseEntity<List<Take>> queryByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
		if (page < 0 || size < 0)
			return ResponseEntity.badRequest().build();
		List<Take> takes = this.takeService.queryByPage(page, size);
		if (takes == null || takes.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(takes);
	}

	/**
	 * 查询所有数据
	 *
	 * @return 查询结果
	 */
	@GetMapping("all")
	public ResponseEntity<List<Take>> queryAll() {
		return ResponseEntity.ok(this.takeService.queryAll());
	}

	/**
	 * 通过主键查询单条数据
	 *
	 * @param id 主键
	 * @return 单条数据
	 */
	@GetMapping("{id}")
	public ResponseEntity<Take> queryById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(this.takeService.queryById(id));
	}

	/**
	 * 新增数据
	 *
	 * @param take 实体
	 * @return 新增结果
	 */
	@PostMapping
	public ResponseEntity<Take> add(@RequestBody Take take) {
		if (this.takeService.existsById(take.getId()))
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(this.takeService.save(take));
	}

	/**
	 * 编辑数据
	 *
	 * @param take 实体
	 * @return 编辑结果
	 */
	@PutMapping
	public ResponseEntity<Take> edit(@RequestBody Take take) {
		if (!this.takeService.existsById(take.getId()))
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(this.takeService.save(take));
	}

	/**
	 * 删除数据
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	@DeleteMapping
	public ResponseEntity<Boolean> deleteById(Integer id) {
		if (!this.takeService.existsById(id))
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(this.takeService.deleteById(id));
	}

	@GetMapping("user/{id}")
	public ResponseEntity<List<Integer>> queryByUserId(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(this.takeService.queryByUserId(id));
	}
}
