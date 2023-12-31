package com.tongji.web.webchatbackend.repository;

import com.tongji.web.webchatbackend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * (Message)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-29 21:10:21
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>, Serializable {
	List<Message> findByMsgFromAndMsgTo(Integer userId, Integer friendId);
}
