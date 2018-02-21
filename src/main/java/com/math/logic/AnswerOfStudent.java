package com.math.logic;

public class AnswerOfStudent {
	private Integer id;
	private Answer answer;
	private Task task; // Consists also true answer_id
	private TaskForStudent taskForStudent;
	
	public AnswerOfStudent(Answer answer, TaskForStudent taskForStudent) {
		this.answer = answer;
		this.taskForStudent = taskForStudent;
	}
	
	public AnswerOfStudent(Integer id, Answer answer, TaskForStudent taskForStudent, Task task) {
		this(answer, taskForStudent);
		this.id = id;
		this.task = task;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public TaskForStudent getTaskForStudent() {
		return taskForStudent;
	}

	public void setTaskForStudent(TaskForStudent taskForStudent) {
		this.taskForStudent = taskForStudent;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
}
