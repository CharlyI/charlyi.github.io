package com.math.logic;

public class Test {
	private Integer id;
	private String title;
	private Integer teacher_id;
	private Integer subsection_id;
	
	public Test(String title) {
		this.title = title;
	}
	
	public Test(Integer id, String title) {
		this(title);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTeacherId() {
		return teacher_id;
	}

	public void setTeacherId(Integer teacher_id) {
		this.teacher_id = teacher_id;
	}

	public Integer getSubsectionId() {
		return subsection_id;
	}

	public void setSubsectionId(Integer subsection_id) {
		this.subsection_id = subsection_id;
	}
}
