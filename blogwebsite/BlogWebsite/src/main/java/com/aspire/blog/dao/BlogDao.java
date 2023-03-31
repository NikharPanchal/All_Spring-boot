package com.aspire.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aspire.blog.utils.User;

@Repository
public interface BlogDao extends JpaRepository<User, Integer> {

}
