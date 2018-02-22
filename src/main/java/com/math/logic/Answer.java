package com.math.logic;

public class Answer {
	private Integer id;
	private String answer;
	private Integer task_id;
	
	public Answer(String answer) {
		this.answer = answer;
	}
	
	public Answer(Integer id, String answer) {
		this(answer);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getTaskId() {
		return task_id;
	}

	public void setTaskId(Integer task_id) {
		this.task_id = task_id;
	}
}
