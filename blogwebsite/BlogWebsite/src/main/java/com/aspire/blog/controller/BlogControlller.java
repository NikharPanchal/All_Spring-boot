package com.aspire.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspire.blog.service.BlogService;
import com.aspire.blog.utils.User;

@RestController
@RequestMapping("/blog")
public class BlogControlller {

	@Autowired
	private BlogService service;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> userList = null;
		try {
		userList=service.getAllUser();
		}catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.accepted().body(userList);
	}
	
	@PostMapping("/save")
	public ResponseEntity<User> saveUserDetails(@RequestBody User user) {
		User userResponse = null;
		try {
		 userResponse=service.saveUserInfo(user);
		}catch(Exception e)
		{
			e.printStackTrace();
			ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.accepted().body(userResponse);
	}
	
	@GetMapping("/hasuser")
	public ResponseEntity<List<User>> getAllHasUser(){
		List<User> userList = null;
		try {
		System.out.println("***");
		userList=service.getAllHasUser();
		System.out.println(userList);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.accepted().body(userList);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteUser(@PathVariable("id") Integer id) {
		try {
			service.deleteUser(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed");
			
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Delete Success");
	}

}
