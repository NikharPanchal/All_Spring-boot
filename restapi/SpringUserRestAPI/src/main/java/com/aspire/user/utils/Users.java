package com.aspire.user.utils;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class Users{
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Column
	private String userName;
	@Column
	private String userPassword;
	@Column
	private String userAddress;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column
	private Date dob;
	
	private String role;
	
	
	

	@Override
	public String toString() {
		return "Users [id=" + id + ", userName=" + userName + ", userPassword=" + userPassword + ", userAddress="
				+ userAddress + ", dob=" + dob + "]";
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Users() {
		
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = "User";
	}

	public Users(int id, String userName, String userPassword, String userAddress, Date dob, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userAddress = userAddress;
		this.dob = dob;
		this.role = role;
	}
	
	
}
