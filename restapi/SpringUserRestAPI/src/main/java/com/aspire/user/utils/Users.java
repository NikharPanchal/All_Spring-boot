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
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

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
	
	@Column
	private String role;
	
	@Transient
	private Collection<? extends GrantedAuthority> authorities;
	
//	@Column
//	@Enumerated(EnumType.STRING)
//	@ElementCollection(fetch=FetchType.EAGER)
//	private Set<Role> role=new HashSet<>();

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

	public Users(String userName, String userPassword, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.setAuthorities(authorities);
	}


	

	public Users(int id, String userName, String userPassword, String userAddress, Date dob,
			String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userAddress = userAddress;
		this.dob = dob;
		this.role=role;
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Set<GrantedAuthority> authorities=new HashSet<>();
//		for(Role r: this.getRole())
//		{	
//			SimpleGrantedAuthority auth=new SimpleGrantedAuthority(r.name());
//			authorities.add(auth);
//		}
//		return authorities;
//	}


	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
}
