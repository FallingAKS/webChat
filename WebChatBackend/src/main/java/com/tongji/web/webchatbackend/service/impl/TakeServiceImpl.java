package com.tongji.web.webchatbackend.service.impl;

import com.tongji.web.webchatbackend.entity.Take;
import com.tongji.web.webchatbackend.repository.TakeRepository;
import com.tongji.web.webchatbackend.service.TakeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * (Take)表服务实现类
 *
 * @author makejava
 * @since 2023-12-29 20:59:04
 */
@Service
public class TakeServiceImpl implements TakeService {
	@Resource
	private TakeRepository takeRepository;

	/**
	 * 分页查询
	 *
	 * @param page 页数
	 * @param size 条目数
	 * @return 查询结果
	 */
	@Override
	public List<Take> queryByPage(int page, int size) {
		List<Take> takes = this.takeRepository.findAll();
		int        total = takes.size();
		if (page * size >= total)
			return null;
		return takes.subList(page * size, Math.min((page + 1) * size, total));
	}

	/**
	 * 通过ID查询单条数据
	 *
	 * @param id 主键
	 * @return 实例对象
	 */
	@Override
	public Take queryById(Integer id) {
		return this.takeRepository.findById(id).orElse(null);
	}

	/**
	 * 通过ID查询所有数据
	 *
	 * @return 实例对象
	 */
	@Override
	public List<Take> queryAll() {
		return this.takeRepository.findAll();
	}

	/**
	 * 通过ID查询数据是否存在
	 *
	 * @param id 主键
	 * @return 是否存在相同id的记录
	 */
	@Override
	public boolean existsById(Integer id) {
		return this.takeRepository.existsById(id);
	}

	/**
	 * 新增数据
	 *
	 * @param take 实例对象
	 * @return 实例对象
	 */
	@Override
	public Take save(Take take) {
		this.takeRepository.save(take);
		return take;
	}

	/**
	 * 通过主键删除数据
	 *
	 * @param id 主键
	 * @return 是否成功
	 */
	@Override
	public boolean deleteById(Integer id) {
		this.takeRepository.deleteById(id);
		return this.takeRepository.findById(id).orElse(null) == null;
	}

	@Override
	public List<Integer> queryByUserId(Integer id) {
		List<Take> takes = this.takeRepository.queryByUserId(id);

		List<Integer> result = new ArrayList<>();
		for (Take take : takes) {
			result.add(take.getGroupId());
		}
		return result;
	}
}
