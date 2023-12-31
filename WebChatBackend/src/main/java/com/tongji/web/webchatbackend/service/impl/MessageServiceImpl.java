package com.tongji.web.webchatbackend.service.impl;

import com.tongji.web.webchatbackend.entity.Message;
import com.tongji.web.webchatbackend.repository.MessageRepository;
import com.tongji.web.webchatbackend.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * (Message)表服务实现类
 *
 * @author makejava
 * @since 2023-12-29 21:10:21
 */
@Service
public class MessageServiceImpl implements MessageService {
	@Resource
	private MessageRepository messageRepository;

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	@Override
	public List<Message> queryByPage(int page, int size) {
		List<Message> messages = this.messageRepository.findAll();
		int           total    = messages.size();
		if (page * size >= total)
			return null;
		return messages.subList(page * size, Math.min((page + 1) * size, total));
	}

	/**
	 * 通过ID查询单条数据
	 *
	 * @param msgId 主键
	 * @return 实例对象
	 */
	@Override
	public Message queryByMsgId(Integer msgId) {
		return this.messageRepository.findById(msgId).orElse(null);
	}

	/**
	 * 通过ID查询所有数据
	 *
	 * @return 实例对象
	 */
	@Override
	public List<Message> queryAll() {
		return this.messageRepository.findAll();
	}

	/**
	 * 通过ID查询数据是否存在
	 *
	 * @param msgId 主键
	 * @return 是否存在相同msgId的记录
	 */
	@Override
	public boolean existsByMsgId(Integer msgId) {
		return this.messageRepository.existsById(msgId);
	}

	/**
	 * 新增数据
	 *
	 * @param message 实例对象
	 * @return 实例对象
	 */
	@Override
	public Message save(Message message) {
		this.messageRepository.save(message);
		return message;
	}

	/**
	 * 通过主键删除数据
	 *
	 * @param msgId 主键
	 * @return 是否成功
	 */
	@Override
	public boolean deleteByMsgId(Integer msgId) {
		this.messageRepository.deleteById(msgId);
		return this.messageRepository.findById(msgId).orElse(null) == null;
	}

	@Override
	public List<Message> queryByUserIdAndFriendId(Integer userId, Integer friendId) {
		List<Message> messagesFrom = this.messageRepository.findByMsgFromAndMsgTo(userId, friendId);
		List<Message> messagesTo   = this.messageRepository.findByMsgFromAndMsgTo(friendId, userId);

		messagesFrom.addAll(messagesTo);
		// 按照id排序
		messagesFrom.sort(Comparator.comparing(Message::getMsgId));
		return messagesFrom;
	}
}
