package com.aspire.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.aspire.blog.utils.Users;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = "http://localhost:4200")
public class BlogControlller {

	@Autowired
	private BlogService service;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAllUser() {
		List<Users> userList = null;
		try {
			userList = service.getAllUser();
		} catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.accepted().body(userList);
	}

	@PostMapping("/save")
	public ResponseEntity<Users> saveUserDetails(@RequestBody Users user) {
		Users userResponse = null;
		try {
			userResponse = service.saveUserInfo(user);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.accepted().body(userResponse);
	}

	@GetMapping("/hasuser")
	public ResponseEntity<List<Users>> getAllHasUser() {
		List<Users> userList = null;
		try {
			System.out.println("***");
			userList = service.getAllHasUser();
			System.out.println(userList);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.accepted().body(userList);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteUser(@PathVariable("id") Integer id) {
		try {
			service.deleteUser(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed");

		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Delete Success");
	}

	@GetMapping("/userbyid/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable("id") Integer id) {
		Users user = null;
		try {
			user = service.getUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.accepted().body(user);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Users> updateUserInfo(@PathVariable("id") Integer id, @RequestBody Users user) {
		Users userResponse = null;
		Users getUserByid = null;
		try {
			getUserByid = service.getUserById(id);
			System.out.println(getUserByid);
			if (getUserByid != null) {
				userResponse = service.saveUserInfo(user);
				System.out.println(user);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.accepted().body(userResponse);
	}

	@PutMapping("/status/{id}")
	public ResponseEntity updateStatus(@PathVariable("id") Integer userId) {
		try {
			service.updateUserStatus(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Update Status success");
	}

	@PostMapping("/logincredential")
	public ResponseEntity checkLoginCredential(@RequestBody Users user) {

		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		Boolean response = false;
		try {
			response = service.checkLoginData(user);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if (response) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Login credential are valid");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid credential");
		}
	}

}