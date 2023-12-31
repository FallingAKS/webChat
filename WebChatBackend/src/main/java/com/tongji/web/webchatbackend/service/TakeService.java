package com.tongji.web.webchatbackend.service;

import com.tongji.web.webchatbackend.entity.Take;

import java.util.List;

/**
 * (Take)表服务接口
 *
 * @author makejava
 * @since 2023-12-29 20:59:04
 */
public interface TakeService {

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	List<Take> queryByPage(int page, int size);

	/**
	 * 通过ID查询单条数据
	 *
	 * @param id 主键
	 * @return 实例对象
	 */
	Take queryById(Integer id);

	/**
	 * 通过ID查询所有数据
	 *
	 * @return 实例对象
	 */
	List<Take> queryAll();

	/**
	 * 通过ID查询数据是否存在
	 *
	 * @param id 主键
	 * @return 是否存在相同id的记录
	 */
	boolean existsById(Integer id);

	/**
	 * 保存数据
	 *
	 * @param take 实例对象
	 * @return 实例对象
	 */
	Take save(Take take);

	/**
	 * 通过主键删除数据
	 *
	 * @param id 主键
	 * @return 是否成功
	 */
	boolean deleteById(Integer id);

	List<Integer> queryByUserId(Integer id);
}
