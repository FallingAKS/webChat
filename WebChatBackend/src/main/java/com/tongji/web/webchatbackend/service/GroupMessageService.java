package com.tongji.web.webchatbackend.service;

import com.tongji.web.webchatbackend.entity.GroupMessage;

import java.util.List;

/**
 * (GroupMessage)表服务接口
 *
 * @author makejava
 * @since 2023-12-29 21:21:40
 */
public interface GroupMessageService {

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	List<GroupMessage> queryByPage(int page, int size);

	/**
	 * 通过ID查询单条数据
	 *
	 * @param groupMsgId 主键
	 * @return 实例对象
	 */
	GroupMessage queryByGroupMsgId(Integer groupMsgId);

	/**
	 * 通过ID查询所有数据
	 *
	 * @return 实例对象
	 */
	List<GroupMessage> queryAll();

	/**
	 * 通过ID查询数据是否存在
	 *
	 * @param groupMsgId 主键
	 * @return 是否存在相同groupMsgId的记录
	 */
	boolean existsByGroupMsgId(Integer groupMsgId);

	/**
	 * 保存数据
	 *
	 * @param groupMessage 实例对象
	 * @return 实例对象
	 */
	GroupMessage save(GroupMessage groupMessage);

	/**
	 * 通过主键删除数据
	 *
	 * @param groupMsgId 主键
	 * @return 是否成功
	 */
	boolean deleteByGroupMsgId(Integer groupMsgId);

	List<GroupMessage> queryByGroupId(Integer groupId);
}
