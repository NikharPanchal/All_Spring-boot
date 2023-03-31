package com.aspire.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aspire.blog.utils.User;

@Repository
public interface BlogDao extends JpaRepository<User, Integer> {

	@Query(value = "SELECT * FROM user_table where role='user'",nativeQuery = true)
	List<User> findAllHasUser();

}
