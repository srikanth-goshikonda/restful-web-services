package com.example.demo;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class User {

	private Integer id;
	@Size(min = 2)
	private String name;
	@Email(message = "Email Not Valid")
	private String email;
	private Date birthDate;

	public User() {
		super();
	}

	public User(Integer id, String name,String email, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	

}
