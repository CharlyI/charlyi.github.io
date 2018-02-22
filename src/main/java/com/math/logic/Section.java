package com.math.logic;

public class Section {
	private String title;
	private Integer id;
	
	public Section(String title) {
		this.title = title;
	}
	
	public Section(Integer id, String title) {
		this(title);
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
