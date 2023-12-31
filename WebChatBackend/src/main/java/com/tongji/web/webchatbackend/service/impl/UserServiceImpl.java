package com.tongji.web.webchatbackend.service.impl;

import com.tongji.web.webchatbackend.entity.User;
import com.tongji.web.webchatbackend.repository.UserRepository;
import com.tongji.web.webchatbackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-12-29 20:19:22
 */
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserRepository userRepository;

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	@Override
	public List<User> queryByPage(int page, int size) {
		List<User> users = this.userRepository.findAll();
		int        total = users.size();
		if (page * size >= total)
			return null;
		return users.subList(page * size, Math.min((page + 1) * size, total));
	}

	/**
	 * 通过ID查询单条数据
	 *
	 * @param userId 主键
	 * @return 实例对象
	 */
	@Override
	public User queryByUserId(Integer userId) {
		return this.userRepository.findById(userId).orElse(null);
	}

	/**
	 * 通过ID查询所有数据
	 *
	 * @return 实例对象
	 */
	@Override
	public List<User> queryAll() {
		return this.userRepository.findAll();
	}

	/**
	 * 通过ID查询数据是否存在
	 *
	 * @param userId 主键
	 * @return 是否存在相同userId的记录
	 */
	@Override
	public boolean existsByUserId(Integer userId) {
		return this.userRepository.existsById(userId);
	}

	/**
	 * 新增数据
	 *
	 * @param user 实例对象
	 * @return 实例对象
	 */
	@Override
	public User save(User user) {
		this.userRepository.save(user);
		return user;
	}

	/**
	 * 通过主键删除数据
	 *
	 * @param userId 主键
	 * @return 是否成功
	 */
	@Override
	public boolean deleteByUserId(Integer userId) {
		this.userRepository.deleteById(userId);
		return this.userRepository.findById(userId).orElse(null) == null;
	}

	@Override
	public boolean existsByNameAndPassword(String name, String password) {
		return this.userRepository.existsByNameAndPassword(name, password);
	}

	@Override
    public boolean existsByName(String name) {
        return this.userRepository.existsByName(name);
    }

	@Override
	public User queryByName(String name) {
		return this.userRepository.findByName(name);
	}
}
