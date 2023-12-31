package com.tongji.web.webchatbackend.repository;

import com.tongji.web.webchatbackend.entity.GroupMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * (GroupMessage)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-29 21:21:40
 */
@Repository
public interface GroupMessageRepository extends JpaRepository<GroupMessage, Integer>, Serializable {
	List<GroupMessage> findByGroupId(Integer groupId);
}
