package com.aspire.user.utils;

import java.io.InputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "image_data")
public class Images {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private
	int id;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private
	String image;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private
	InputStream image_string;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Images(int id, String image) {
		super();
		this.id = id;
		this.image = image;
	}

	public Images() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InputStream getImage_string() {
		return image_string;
	}

	public void setImage_string(InputStream image_string) {
		this.image_string = image_string;
	}	
}
