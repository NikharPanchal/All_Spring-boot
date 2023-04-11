package com.aspire.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspire.blog.dao.BlogDao;
import com.aspire.blog.utils.Blog;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;

	public Blog saveUserBlog(Blog blog) {
		
		Blog blogResponse=blogDao.save(blog);
		
		return blogResponse;
	}

	public List<Blog> fetchAllBlogs() {
		
		List<Blog> BlogList=blogDao.findAll();
		
		return BlogList;
	}

	public void deleteBlog(Integer id) {
		blogDao.deleteById(id);
	}

	public List<Blog> getBlogByEmail(String email) {
		List<Blog> blogList=blogDao.findAllByEmail(email);
		return blogList;
	}

}
