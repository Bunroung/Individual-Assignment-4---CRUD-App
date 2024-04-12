package com.bunroung.crud.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class UserCreate {
	@NotEmpty(message = "The name is required")
	private String name;
	
	@NotEmpty(message = "The email is required")
	private String email;
	
	@Min(0)
	private int number;
	
	@NotEmpty(message = "The password is required")
	private String password;

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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
