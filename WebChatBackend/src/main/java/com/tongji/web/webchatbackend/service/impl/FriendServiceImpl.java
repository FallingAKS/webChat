package com.tongji.web.webchatbackend.service.impl;

import com.tongji.web.webchatbackend.entity.Friend;
import com.tongji.web.webchatbackend.repository.FriendRepository;
import com.tongji.web.webchatbackend.service.FriendService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * (Friend)表服务实现类
 *
 * @author makejava
 * @since 2023-12-29 20:39:02
 */
@Service
public class FriendServiceImpl implements FriendService {
	@Resource
	private FriendRepository friendRepository;

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	@Override
	public List<Friend> queryByPage(int page, int size) {
		List<Friend> friends = this.friendRepository.findAll();
		int          total   = friends.size();
		if (page * size >= total)
			return null;
		return friends.subList(page * size, Math.min((page + 1) * size, total));
	}

	/**
	 * 通过ID查询单条数据
	 *
	 * @param id 主键
	 * @return 实例对象
	 */
	@Override
	public Friend queryById(Integer id) {
		return this.friendRepository.findById(id).orElse(null);
	}

	/**
	 * 通过ID查询所有数据
	 *
	 * @return 实例对象
	 */
	@Override
	public List<Friend> queryAll() {
		return this.friendRepository.findAll();
	}

	/**
	 * 通过ID查询数据是否存在
	 *
	 * @param id 主键
	 * @return 是否存在相同id的记录
	 */
	@Override
	public boolean existsById(Integer id) {
		return this.friendRepository.existsById(id);
	}

	/**
	 * 新增数据
	 *
	 * @param friend 实例对象
	 * @return 实例对象
	 */
	@Override
	public Friend save(Friend friend) {
		this.friendRepository.save(friend);
		return friend;
	}

	/**
	 * 通过主键删除数据
	 *
	 * @param id 主键
	 * @return 是否成功
	 */
	@Override
	public boolean deleteById(Integer id) {
		this.friendRepository.deleteById(id);
		return this.friendRepository.findById(id).orElse(null) == null;
	}

	@Override
	public List<Integer> queryByUserId(Integer id) {
		List<Friend> users  = this.friendRepository.queryByUserId(id);
		List<Friend> users2 = this.friendRepository.queryByFriendId(id);

		List<Integer> friends = new ArrayList<>();
		for (Friend user : users) {
			friends.add(user.getFriendId());
		}
		for (Friend user : users2) {
			if (!friends.contains(user.getUserId()))
				friends.add(user.getUserId());
		}
		return friends;
	}

	@Override
	public boolean existsByUserIdAndFriendId(Integer userId, Integer friendId) {
		return this.friendRepository.existsByUserIdAndFriendId(userId, friendId);
	}

	@Override
	public Boolean deleteByUserIdAndFriendId(Integer userId, Integer friendId) {
		if (this.friendRepository.existsByUserIdAndFriendId(userId, friendId)) {
			this.friendRepository.deleteById(this.friendRepository.findByUserIdAndFriendId(userId, friendId).getId());
		} else if (this.friendRepository.existsByUserIdAndFriendId(friendId, userId)) {
			this.friendRepository.deleteById(this.friendRepository.findByUserIdAndFriendId(friendId, userId).getId());
		} else {
			return false;
		}
		return !this.friendRepository.existsByUserIdAndFriendId(userId, friendId);
	}
}
