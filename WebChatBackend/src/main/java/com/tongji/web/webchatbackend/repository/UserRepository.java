package com.tongji.web.webchatbackend.repository;

import com.tongji.web.webchatbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-29 20:19:22
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, Serializable {
	boolean existsByNameAndPassword(String name, String password);

	boolean existsByName(String name);

	User findByName(String name);
}
