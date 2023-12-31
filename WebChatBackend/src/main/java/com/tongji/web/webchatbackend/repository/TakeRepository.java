package com.tongji.web.webchatbackend.repository;

import com.tongji.web.webchatbackend.entity.Take;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * (Take)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-29 20:59:04
 */
@Repository
public interface TakeRepository extends JpaRepository<Take, Integer>, Serializable {
	List<Take> queryByUserId(Integer id);
}
