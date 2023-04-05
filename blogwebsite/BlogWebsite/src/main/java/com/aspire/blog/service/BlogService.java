package com.aspire.blog.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aspire.blog.dao.BlogDao;
import com.aspire.blog.utils.Users;

@Service
public class BlogService implements UserDetailsService{

	@Autowired
	BlogDao blogDao;
	
	public List<Users> getAllUser() {
		List<Users> userList=blogDao.findAll();
		return userList;
	}

	public Users saveUserInfo(Users user) {
		Users userData=blogDao.save(user);
		return userData;
	}

	public List<Users> getAllHasUser() {
		List<Users> userList=blogDao.findAllHasUser();
		return userList;
	}

	public void deleteUser(Integer id) {
		blogDao.deleteById(id);
	
	}

	public Users getUserById(Integer id) {
		Users user=blogDao.findById(id).orElse(null);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		Users user= blogDao.findByEmail(username);
		System.out.println(user);
		if(user==null)
		{
			throw new UsernameNotFoundException("user not found by given username ");
		}
		
		List<SimpleGrantedAuthority> authority=Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
		System.out.println(authority);
		
		
		return new User(user.getEmail(),user.getPassword(),authority);
	}

	public void updateUserStatus(Integer userId) {
		blogDao.updateUserStatus(userId);
	}

	public Boolean checkLoginData(Users user) {
		Boolean email=blogDao.existsByEmail(user.getEmail());
		Boolean password=blogDao.existsByPassword(user.getPassword());
		if(email&&password)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
