package com.math.logic;

public abstract class AbstractUser {
	
	private Integer id;
	private String email;
	private String firstname;
	private String lastname;
	private String patronymic;
	
	public AbstractUser() {
		// Default constructor
	}
	
	public AbstractUser(String firstname, String lastname, String patronymic, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.patronymic = patronymic;
		this.email = email;
	}
	
	public AbstractUser(Integer id, String firstname, String lastname, String patronymic, String email) {
		this(firstname, lastname, patronymic, email);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	
	public String getFullName() {
		String fio = new String(this.getLastname().concat(" ").concat(this.getFirstname().concat(" ").concat(this.getPatronymic())));
		return fio;
	}

}



