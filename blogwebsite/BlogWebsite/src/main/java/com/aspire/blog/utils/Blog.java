package com.aspire.blog.utils;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.mysql.cj.jdbc.Blob;


@Entity
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int blogId;
	
	@Column(name="title",nullable = false)
	private String blogTitle;
	
	@Column(name="description",nullable = false,length = 5000)
	private String blogDescription;
	
	@Column(name="user_email",nullable = false)
	private String email;

	@Column(name="blog_image_name")
	private String image;
	
	@Column(name="image_byte",length=10000)
	private byte[] imageByte;
	
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


	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", blogTitle=" + blogTitle + ", blogDescription=" + blogDescription
				+ ", email=" + email + ", image=" + image + ", imageByte=" + Arrays.toString(imageByte) + "]";
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public byte[] getImageByte() {
		return imageByte;
	}

	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}

	public Blog(int blogId, String blogTitle, String blogDescription, String email, String image, byte[] imageByte) {
		super();
		this.blogId = blogId;
		this.blogTitle = blogTitle;
		this.blogDescription = blogDescription;
		this.email = email;
		this.image = image;
		this.imageByte = imageByte;
	}

}
