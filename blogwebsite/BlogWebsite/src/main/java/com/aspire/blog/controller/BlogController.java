package com.aspire.blog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aspire.blog.service.BlogService;
import com.aspire.blog.utils.Blog;
import com.aspire.blog.utils.FileUploadUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/blogs")
@CrossOrigin(origins = "http://localhost:4200")
public class BlogController {

	
	@Autowired
	private BlogService blogService;
	
	@PostMapping("/saveBlog")
	public ResponseEntity<Blog> saveUserBlog(@RequestParam("file") MultipartFile file, @RequestParam("blog") String blog) throws IOException {
		
		Blog blogData=new ObjectMapper().readValue(blog, Blog.class);
		
		Blog blogResponse=null;
		
		System.out.println(blog);
		if (!file.isEmpty()) {
			String cleanPath = StringUtils.cleanPath(file.getOriginalFilename());
			//System.out.println(cleanPath);
			blogData.setImage(cleanPath);
			blogData.setImageByte(file.getBytes());
			blogResponse=blogService.saveUserBlog(blogData);
			String upload = "images/";

			FileUploadUtil.saveFile(upload, cleanPath, file);
		} else {
			if (blogData.getImage().isEmpty()) {
				blogData.setImage(null);
				blogService.saveUserBlog(blogData);
			}
		}
		
		//System.out.println(blog);
		
		return ResponseEntity.ok(blogResponse);
	}
	
	@GetMapping("/getAllBlog")
	public ResponseEntity<List<Blog>> getAllBlogs() {
		
		List<Blog> blogList=blogService.fetchAllBlogs();
		
		return ResponseEntity.status(HttpStatus.OK).body(blogList);
	}
	
	@DeleteMapping("/deleteBlog/{id}")
	public ResponseEntity deleteBlog(@PathVariable("id") Integer id) {
		try {
			blogService.deleteBlog(id);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/getBlogById/{username}")
	public ResponseEntity getBlogByUsername(@PathVariable("username") String email)
	{
		List<Blog> blogList=null;
		try {
			blogList=blogService.getBlogByEmail(email);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(blogList);
	}
	
	@GetMapping("/findBlogById/{id}")
	public ResponseEntity getBlogById(@PathVariable("id") int id) {
		List<Blog> blogList=null;
		try {
			blogList=blogService.getBlogById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(blogList);
	}
	
	@PostMapping("/updateBlog")
	public ResponseEntity<Blog> updateUserBlog(@RequestPart(name="file",required = false) MultipartFile file, @RequestParam("blog") String blog) throws IOException {
		
		Blog blogData=new ObjectMapper().readValue(blog, Blog.class);
		
		System.out.println(blogData.getImage());
		System.out.println(blog);
		
		Blog blogResponse=null;
		
		if (!file.isEmpty()) {
			String cleanPath = StringUtils.cleanPath(file.getOriginalFilename());
			//System.out.println(cleanPath);
			blogData.setImage(cleanPath);
			blogData.setImageByte(file.getBytes());
			blogResponse=blogService.saveUserBlog(blogData);
			String upload = "images/";

			FileUploadUtil.saveFile(upload, cleanPath, file);
		}else
		{
			System.out.println("file is null");
		}
		
		//System.out.println(blog);
		
		return ResponseEntity.ok(blogResponse);
	}
}
