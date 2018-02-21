package com.math.logic;

public class Teacher extends AbstractUser {

	private Integer userId;
	
	public Teacher() {
		// Default constructor
	}
	
	public Teacher(String firstname, String lastname, String patronymic, String email) {
		super(firstname, lastname, patronymic, email);
	}
	
	public Teacher(Integer id, String firstname, String lastname, String patronymic, String email) {
		super(id, firstname, lastname, patronymic, email);
	}
	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
