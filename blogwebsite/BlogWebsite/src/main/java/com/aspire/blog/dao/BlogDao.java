package com.aspire.blog.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aspire.blog.utils.JwtToken;
import com.aspire.blog.utils.User;

@Repository
public interface BlogDao extends JpaRepository<User, Integer> {

	@Query(value = "SELECT * FROM user_table where role='user'",nativeQuery = true)
	List<User> findAllHasUser();

	@Modifying
	@Transactional
	@Query(value = "UPDATE user_table SET isactive=true WHERE id=?1 ",nativeQuery = true)
	void updateUserStatus(int userId);

	Boolean existsByEmail(String string);

	Boolean existsByPassword(String password);

//	@Query(value="SELECT * from user_table WHERE email=?1",nativeQuery = true)
//	public User findByEmailAddress(String email);

}


