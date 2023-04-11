package com.aspire.blog.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aspire.blog.utils.JwtToken;
import com.aspire.blog.utils.Users;

@Repository
public interface UserDao extends JpaRepository<Users, Integer> {

	@Query(value = "SELECT * FROM user_table where role='user'",nativeQuery = true)
	List<Users> findAllHasUser();

	@Modifying
	@Transactional
	@Query(value = "UPDATE user_table SET isactive=true WHERE id=?1 ",nativeQuery = true)
	void updateUserStatus(int userId);

	Boolean existsByEmail(String string);

	Boolean existsByPassword(String password);

	Users findByEmail(String username);

	@Query(value="SELECT * FROM user_table where email=?1 and isactive=1",nativeQuery = true)
	Users userIsActive(String email);


//	@Query(value="SELECT * from user_table WHERE email=?1",nativeQuery = true)
//	public User findByEmailAddress(String email);

}


