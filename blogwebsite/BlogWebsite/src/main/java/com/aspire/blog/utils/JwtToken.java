package com.aspire.blog.utils;

public class JwtToken {
	
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public JwtToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JwtToken(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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

}
