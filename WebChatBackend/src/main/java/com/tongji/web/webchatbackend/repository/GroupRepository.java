package com.tongji.web.webchatbackend.repository;

import com.tongji.web.webchatbackend.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * (Group)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-29 21:08:12
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Integer>, Serializable {
	Group findByGroupId(Integer groupId);
}
