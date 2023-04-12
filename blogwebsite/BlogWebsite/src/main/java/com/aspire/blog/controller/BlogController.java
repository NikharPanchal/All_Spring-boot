package com.aspire.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspire.blog.service.BlogService;
import com.aspire.blog.utils.Blog;
import com.aspire.blog.utils.Users;

@RestController
@RequestMapping("/blogs")
@CrossOrigin(origins = "http://localhost:4200")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@PostMapping("/saveBlog")
	public ResponseEntity saveUserBlog(@RequestBody Blog blog) {
		
		System.out.println(blog);
		Blog blogResponse=blogService.saveUserBlog(blog);
		
		return ResponseEntity.ok(blogResponse);
	}
	
	@GetMapping("/getAllBlog")
	public ResponseEntity<List<Blog>> getAllBlogs() {
		
		List<Blog> blogList=blogService.fetchAllBlogs();
		System.out.println(blogList);
		
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
	
	@PutMapping("/updateBlog/{id}")
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") Integer id, @RequestBody Blog blog) {
		Blog blogResponse = null;
		Blog getBlogByid = null;
		try {
			System.out.println("blog :- "+blog);
			getBlogByid = blogService.getBlogByBlogId(id);
			System.out.println(getBlogByid);
			if (getBlogByid != null) {
				blogResponse = blogService.saveUserBlog(blog);
				
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.accepted().body(blogResponse);
	}
}
