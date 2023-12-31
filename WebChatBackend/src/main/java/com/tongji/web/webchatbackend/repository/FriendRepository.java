package com.tongji.web.webchatbackend.repository;

import com.tongji.web.webchatbackend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.List;

/**
 * (Friend)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-29 20:39:02
 */
@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer>, Serializable {
	List<Friend> queryByUserId(Integer userId);

	List<Friend> queryByFriendId(Integer id);

	boolean existsByUserIdAndFriendId(Integer userId, Integer friendId);

	Friend findByUserIdAndFriendId(Integer userId, Integer friendId);
}
