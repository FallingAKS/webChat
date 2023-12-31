package com.tongji.web.webchatbackend.service.impl;

import com.tongji.web.webchatbackend.entity.GroupMessage;
import com.tongji.web.webchatbackend.repository.GroupMessageRepository;
import com.tongji.web.webchatbackend.service.GroupMessageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (GroupMessage)表服务实现类
 *
 * @author makejava
 * @since 2023-12-29 21:21:40
 */
@Service
public class GroupMessageServiceImpl implements GroupMessageService {
	@Resource
	private GroupMessageRepository groupMessageRepository;

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	@Override
	public List<GroupMessage> queryByPage(int page, int size) {
		List<GroupMessage> groupMessages = this.groupMessageRepository.findAll();
		int                total         = groupMessages.size();
		if (page * size >= total)
			return null;
		return groupMessages.subList(page * size, Math.min((page + 1) * size, total));
	}

	/**
	 * 通过ID查询单条数据
	 *
	 * @param groupMsgId 主键
	 * @return 实例对象
	 */
	@Override
	public GroupMessage queryByGroupMsgId(Integer groupMsgId) {
		return this.groupMessageRepository.findById(groupMsgId).orElse(null);
	}

	/**
	 * 通过ID查询所有数据
	 *
	 * @return 实例对象
	 */
	@Override
	public List<GroupMessage> queryAll() {
		return this.groupMessageRepository.findAll();
	}

	/**
	 * 通过ID查询数据是否存在
	 *
	 * @param groupMsgId 主键
	 * @return 是否存在相同groupMsgId的记录
	 */
	@Override
	public boolean existsByGroupMsgId(Integer groupMsgId) {
		return this.groupMessageRepository.existsById(groupMsgId);
	}

	/**
	 * 新增数据
	 *
	 * @param groupMessage 实例对象
	 * @return 实例对象
	 */
	@Override
	public GroupMessage save(GroupMessage groupMessage) {
		this.groupMessageRepository.save(groupMessage);
		return groupMessage;
	}

	/**
	 * 通过主键删除数据
	 *
	 * @param groupMsgId 主键
	 * @return 是否成功
	 */
	@Override
	public boolean deleteByGroupMsgId(Integer groupMsgId) {
		this.groupMessageRepository.deleteById(groupMsgId);
		return this.groupMessageRepository.findById(groupMsgId).orElse(null) == null;
	}

	@Override
	public List<GroupMessage> queryByGroupId(Integer groupId) {
		return this.groupMessageRepository.findByGroupId(groupId);
	}
}
