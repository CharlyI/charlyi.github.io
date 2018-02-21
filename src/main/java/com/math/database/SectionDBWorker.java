package com.math.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.math.logic.Section;

public class SectionDBWorker {
	public Integer addSection(Section section) {
		Integer subsectionId = null;
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "INSERT INTO sections (title) VALUES (?)";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, section.getTitle());
			statement.executeUpdate();
			
			ResultSet gk = statement.getGeneratedKeys();
			if(gk.next()) {
				subsectionId = gk.getInt(1);// 1 - generated id
			}
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		
		return subsectionId;
	}
	
	public void updateSectionById(Section section, Integer id) {
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "UPDATE sections SET title=? WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, section.getTitle());
			statement.setInt(2, id);
			statement.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();			
		}
	}
	
	
	public List<Section> getAll() {
		List<Section> sections = new ArrayList<Section>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select * from sections";
			PreparedStatement statement = con.prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			
			sections = this.getSectionsList(result);
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return sections;
	}
	
	public void deleteSectionById(Integer id) {
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "DELETE FROM sections WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<Section> getSectionsList(ResultSet result) {
		List<Section> sections = new ArrayList<Section>();
		try {
			while (result.next()) {
				Integer id = result.getInt("id");
				String sectionStr = result.getString("title");
				
				Section section = new Section(id, sectionStr);
				sections.add(section);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sections;
	}
}
