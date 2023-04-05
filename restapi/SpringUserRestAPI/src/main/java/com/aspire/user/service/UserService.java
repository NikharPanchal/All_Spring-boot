package com.aspire.user.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aspire.user.userDao.ImageRepository;
import com.aspire.user.userDao.UserRepository;
import com.aspire.user.utils.Images;
import com.aspire.user.utils.JwtToken;
import com.aspire.user.utils.Users;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	ImageRepository imageRepo;
	 
	public Users saveUserDetails(Users user) {
		return repository.save(user);
	}

	public List<Users> getAllUsers() {
		List<Users> userList=repository.findAll();
		return userList;
	}

	public void deleteUser(int id) {
		 repository.deleteById(id);
	}

	public Users editUserData(Users user) {
		repository.save(user);
		return null;
	}

	public Optional<Users> getUserbyId(int id) {
		Optional<Users> user=repository.findById(id);
		return user;
	}

	public Users findByUsername(String username) {
		Users user=repository.findByUserName(username);
		return user;
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	
//			System.out.println(user);

			System.out.println(username);
			Users user= repository.findByUserName(username);
			System.out.println(user);
			if(user==null)
			{
				throw new UsernameNotFoundException("user not found by given username ");
			}
			
			List<SimpleGrantedAuthority> authority=Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
			System.out.println(authority);
			
			return new User(user.getUserName(),user.getUserPassword(),authority);

	}

	public void saveImage(Images file) {
		
		imageRepo.save(file);	
			
	}

}
