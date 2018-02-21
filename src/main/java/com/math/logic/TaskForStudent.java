package com.math.logic;

import java.util.Date;

public class TaskForStudent {
	private Integer id;
	private Teacher teacher;
	private Student student;
	private Test test;
	private Date dateOfReceiving;
	private Date dateOfPerformance;
	
	public TaskForStudent() {
	
	}
	
	public TaskForStudent(Teacher teacher, Student student, Test test) {
		this.teacher = teacher;
		this.student = student;
		this.test = test;
	}
	
	public TaskForStudent(Integer id, Teacher teacher, Student student, Test test, Date dateOfReceiving, Date dateOfPerformance) {
		this(teacher, student, test);
		this.dateOfReceiving = dateOfReceiving;
		this.dateOfPerformance = dateOfPerformance;
		this.id = id;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Date getDateOfReceiving() {
		return dateOfReceiving;
	}
	public void setDateOfReceiving(Date dateOfReceiving) {
		this.dateOfReceiving = dateOfReceiving;
	}
	public Date getDateOfPerformance() {
		return dateOfPerformance;
	}
	public void setDateOfPerformance(Date dateOfPerformance) {
		this.dateOfPerformance = dateOfPerformance;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}

	public Integer getId() {
		return id;
	}
}
