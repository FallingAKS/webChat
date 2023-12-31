package com.tongji.web.webchatbackend.service.impl;

import com.tongji.web.webchatbackend.entity.Group;
import com.tongji.web.webchatbackend.repository.GroupRepository;
import com.tongji.web.webchatbackend.service.GroupService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Group)表服务实现类
 *
 * @author makejava
 * @since 2023-12-29 21:08:13
 */
@Service
public class GroupServiceImpl implements GroupService {
	@Resource
	private GroupRepository groupRepository;

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	@Override
	public List<Group> queryByPage(int page, int size) {
		List<Group> groups = this.groupRepository.findAll();
		int         total  = groups.size();
		if (page * size >= total)
			return null;
		return groups.subList(page * size, Math.min((page + 1) * size, total));
	}

	/**
	 * 通过ID查询单条数据
	 *
	 * @param groupId 主键
	 * @return 实例对象
	 */
	@Override
	public Group queryByGroupId(Integer groupId) {
		return this.groupRepository.findByGroupId(groupId);
	}

	/**
	 * 通过ID查询所有数据
	 *
	 * @return 实例对象
	 */
	@Override
	public List<Group> queryAll() {
		return this.groupRepository.findAll();
	}

	/**
	 * 通过ID查询数据是否存在
	 *
	 * @param groupId 主键
	 * @return 是否存在相同groupId的记录
	 */
	@Override
	public boolean existsByGroupId(Integer groupId) {
		return this.groupRepository.existsById(groupId);
	}

	/**
	 * 新增数据
	 *
	 * @param group 实例对象
	 * @return 实例对象
	 */
	@Override
	public Group save(Group group) {
		this.groupRepository.save(group);
		return group;
	}

	/**
	 * 通过主键删除数据
	 *
	 * @param groupId 主键
	 * @return 是否成功
	 */
	@Override
	public boolean deleteByGroupId(Integer groupId) {
		this.groupRepository.deleteById(groupId);
		return this.groupRepository.findById(groupId).orElse(null) == null;
	}
}
