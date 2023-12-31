package com.tongji.web.webchatbackend.service;

import com.tongji.web.webchatbackend.entity.Group;

import java.util.List;

/**
 * (Group)表服务接口
 *
 * @author makejava
 * @since 2023-12-29 21:08:12
 */
public interface GroupService {

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	List<Group> queryByPage(int page, int size);

	/**
	 * 通过ID查询单条数据
	 *
	 * @param groupId 主键
	 * @return 实例对象
	 */
	Group queryByGroupId(Integer groupId);

	/**
	 * 通过ID查询所有数据
	 *
	 * @return 实例对象
	 */
	List<Group> queryAll();

	/**
	 * 通过ID查询数据是否存在
	 *
	 * @param groupId 主键
	 * @return 是否存在相同groupId的记录
	 */
	boolean existsByGroupId(Integer groupId);

	/**
	 * 保存数据
	 *
	 * @param group 实例对象
	 * @return 实例对象
	 */
	Group save(Group group);

	/**
	 * 通过主键删除数据
	 *
	 * @param groupId 主键
	 * @return 是否成功
	 */
	boolean deleteByGroupId(Integer groupId);

}
