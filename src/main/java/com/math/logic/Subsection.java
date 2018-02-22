package com.math.logic;

public class Subsection extends Section {
	private Integer section_id;
	
	public Subsection(String title) {
		super(title);
	}
	
	public Subsection(Integer id, String title) {
		super(id, title);
	}
	
	public Subsection(Integer id, String title, Integer section_id) {
		super(id, title);
		this.section_id = section_id;
	}
	
	public Integer getSectionId() {
		return section_id;
	}

	public void setSectionId(Integer section_id) {
		this.section_id = section_id;
	}
}
