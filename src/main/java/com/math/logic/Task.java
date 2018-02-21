package com.math.logic;

import java.util.List;

public class Task {
	private Integer id;
	private String question;
	private Integer trueAnswerId;
	private Integer test_id;
	private List<Answer> answers;
	
	public Task(String question) {
		this.question = question;
	}
	
	public Task(Integer id, String question) {
		this(question);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Integer getTrueAnswerId() {
		return trueAnswerId;
	}
	public void setTrueAnswerId(Integer trueAnswerId) {
		this.trueAnswerId = trueAnswerId;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Integer getTestId() {
		return test_id;
	}

	public void setTestId(Integer test_id) {
		this.test_id = test_id;
	}
}