package com.aspire.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aspire.blog.dao.BlogDao;
import com.aspire.blog.utils.User;

@Service
public class BlogService implements UserDetailsService{

	@Autowired
	BlogDao blogDao;
	
	public List<User> getAllUser() {
		List<User> userList=blogDao.findAll();
		return userList;
	}

	public User saveUserInfo(User user) {
		User userData=blogDao.save(user);
		return userData;
	}

	public List<User> getAllHasUser() {
		List<User> userList=blogDao.findAllHasUser();
		return userList;
	}

	public void deleteUser(Integer id) {
		blogDao.deleteById(id);
	
	}

	public User getUserById(Integer id) {
		User user=blogDao.findById(id).orElse(null);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


}
