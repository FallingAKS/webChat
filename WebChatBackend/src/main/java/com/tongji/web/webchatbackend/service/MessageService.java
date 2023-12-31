package com.tongji.web.webchatbackend.service;

import com.tongji.web.webchatbackend.entity.Message;

import java.util.List;

/**
 * (Message)表服务接口
 *
 * @author makejava
 * @since 2023-12-29 21:10:21
 */
public interface MessageService {

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	List<Message> queryByPage(int page, int size);

	/**
	 * 通过ID查询单条数据
	 *
	 * @param msgId 主键
	 * @return 实例对象
	 */
	Message queryByMsgId(Integer msgId);

	/**
	 * 通过ID查询所有数据
	 *
	 * @return 实例对象
	 */
	List<Message> queryAll();

	/**
	 * 通过ID查询数据是否存在
	 *
	 * @param msgId 主键
	 * @return 是否存在相同msgId的记录
	 */
	boolean existsByMsgId(Integer msgId);

	/**
	 * 保存数据
	 *
	 * @param message 实例对象
	 * @return 实例对象
	 */
	Message save(Message message);

	/**
	 * 通过主键删除数据
	 *
	 * @param msgId 主键
	 * @return 是否成功
	 */
	boolean deleteByMsgId(Integer msgId);

	List<Message> queryByUserIdAndFriendId(Integer userId, Integer friendId);
}
