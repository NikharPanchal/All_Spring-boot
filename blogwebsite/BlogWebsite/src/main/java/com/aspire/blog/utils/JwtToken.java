package com.aspire.blog.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtToken implements UserDetails{
	
	private String email;
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public String getEmail() {
		return email;
	}
	public JwtToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JwtToken(String userName, String userPassword, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.email = userName;
		this.password = userPassword;
		this.authorities = authorities;
	}
	@Override
	public String toString() {
		return "JwtToken [email=" + email + ", password=" + password + "]";
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
