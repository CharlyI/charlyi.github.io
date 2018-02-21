package com.math.logic;

public class User extends AbstractUser {
	
	private String password;
	
	public User() {
		// Default constructor
	}
	
	public User(String firstname, String lastname, String patronymic, String email, String password) {
		super(firstname, lastname, patronymic, email);
		this.password = password;
	}
	
	public User(Integer id, String firstname, String lastname, String patronymic, String email, String password) {
		super(id, firstname, lastname, patronymic, email);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
