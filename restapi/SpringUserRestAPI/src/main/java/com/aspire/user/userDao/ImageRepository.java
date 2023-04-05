package com.aspire.user.userDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aspire.user.utils.Images;

@Repository
public interface ImageRepository extends JpaRepository<Images, Integer>{

}
