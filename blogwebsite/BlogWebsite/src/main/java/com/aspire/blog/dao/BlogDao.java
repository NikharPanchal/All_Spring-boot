package com.aspire.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aspire.blog.utils.Blog;

@Repository
public interface BlogDao extends JpaRepository<Blog, Integer>{

	public List<Blog> findAllByEmail(String email);

	public List<Blog> findAllByblogId(int id);

}
