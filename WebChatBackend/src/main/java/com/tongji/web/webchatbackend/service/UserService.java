package com.tongji.web.webchatbackend.service;

import com.tongji.web.webchatbackend.entity.User;

import java.time.ZoneId;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2023-12-29 20:19:22
 */
public interface UserService {

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	List<User> queryByPage(int page, int size);

	/**
	 * 通过ID查询单条数据
	 *
	 * @param userId 主键
	 * @return 实例对象
	 */
	User queryByUserId(Integer userId);

	/**
	 * 通过ID查询所有数据
	 *
	 * @return 实例对象
	 */
	List<User> queryAll();

	/**
	 * 通过ID查询数据是否存在
	 *
	 * @param userId 主键
	 * @return 是否存在相同userId的记录
	 */
	boolean existsByUserId(Integer userId);

	/**
	 * 保存数据
	 *
	 * @param user 实例对象
	 * @return 实例对象
	 */
	User save(User user);

	/**
	 * 通过主键删除数据
	 *
	 * @param userId 主键
	 * @return 是否成功
	 */
	boolean deleteByUserId(Integer userId);


	boolean existsByNameAndPassword(String name, String password);

	boolean existsByName(String name);

	User queryByName(String name);
}
