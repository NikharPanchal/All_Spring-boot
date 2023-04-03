package com.aspire.user.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtToken implements UserDetails {
	
	private String userName;
	private String userPassword; 
	
	private Collection<? extends GrantedAuthority> authorities;

	
	public String getUserName() {
		return userName;
	}
	@Override
	public String toString() {
		return "JwtToken [userName=" + userName + ", userpPassword=" + userPassword + "]";
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserpassword() {
		return userPassword;
	}
	public void setUserpassword(String userpassword) {
		this.userPassword = userpassword;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.userPassword;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
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
	public JwtToken(String userName, String userPassword, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.authorities = authorities;
	}
	
	
}
