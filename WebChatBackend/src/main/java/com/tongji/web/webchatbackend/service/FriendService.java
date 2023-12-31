package com.tongji.web.webchatbackend.service;

import com.tongji.web.webchatbackend.entity.Friend;

import java.util.List;

/**
 * (Friend)表服务接口
 *
 * @author makejava
 * @since 2023-12-29 20:39:02
 */
public interface FriendService {

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	List<Friend> queryByPage(int page, int size);

	/**
	 * 通过ID查询单条数据
	 *
	 * @param id 主键
	 * @return 实例对象
	 */
	Friend queryById(Integer id);

	/**
	 * 通过ID查询所有数据
	 *
	 * @return 实例对象
	 */
	List<Friend> queryAll();

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
	 * @param friend 实例对象
	 * @return 实例对象
	 */
	Friend save(Friend friend);

	/**
	 * 通过主键删除数据
	 *
	 * @param id 主键
	 * @return 是否成功
	 */
	boolean deleteById(Integer id);

	List<Integer> queryByUserId(Integer id);

	boolean existsByUserIdAndFriendId(Integer userId, Integer friendId);

	Boolean deleteByUserIdAndFriendId(Integer userId, Integer friendId);
}
