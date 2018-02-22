package com.math.logic;

public class Student extends Teacher{
	public Student(String firstname, String lastname, String patronymic, String email) {
		super(firstname, lastname, patronymic, email);
	}
	
	public Student(Integer id, String firstname, String lastname, String patronymic, String email) {
		super(id, firstname, lastname, patronymic, email);
	}
}
