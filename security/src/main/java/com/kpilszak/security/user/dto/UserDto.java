package com.kpilszak.security.user.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

public class UserDto {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	private String occupation;
	
	public UserDto() {
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getOccupation() {
		return occupation;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
