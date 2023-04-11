package com.aspire.blog.utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int blogId;
	
	@Column(name="title",nullable = false)
	private String blogTitle;
	
	@Column(name="description",nullable = false)
	private String blogDescription;
	
	@Column(name="user_email",nullable = false)
	private String email;

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogDescription() {
		return blogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", blogTitle=" + blogTitle + ", blogDescription=" + blogDescription
				+ ", email=" + email + "]";
	}

	public Blog(int blogId, String blogTitle, String blogDescription, String email) {
		super();
		this.blogId = blogId;
		this.blogTitle = blogTitle;
		this.blogDescription = blogDescription;
		this.email = email;
	}

	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
